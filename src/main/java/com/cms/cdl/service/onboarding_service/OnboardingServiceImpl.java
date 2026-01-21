package com.cms.cdl.service.onboarding_service;

import com.cms.cdl.dto.document_dto.DocumentDTO;
import com.cms.cdl.dto.request_dto.onboarding_req_dto.*;
import com.cms.cdl.dto.response_dto.CandidateFullDetailsDTO;
import com.cms.cdl.dto.response_dto.EmployeeDiretoryDetailsDTO;
import com.cms.cdl.dto.response_dto.GeneralListCandidatesData;
import com.cms.cdl.mapper.OnBoardingMapper;
import com.cms.cdl.model.*;
import com.cms.cdl.repository.*;
import com.cms.cdl.util.OnBoardingOperationsCandidates;
import com.cms.cdl.util.OnboardingOperations;
import com.cms.cdl.util.UrlToMultipartFileConvert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class OnboardingServiceImpl implements OnboardingService{

    @Autowired
    private OnboardingOperations onboardingOperations;

    @Autowired
    private EONPersonalRepository eonPersonalRepository;

    @Autowired
    private EONGeneralRepository eonGeneralRepository;

    @Autowired
    private EONEducationRepository eonEducationRepository;

    @Autowired
    private EONSkillRepository eonSkillRepository;

    @Autowired
    private EONFamilyRepository eonFamilyRepository;

    @Autowired
    private EONBankRepository eonBankRepository;
    @Autowired
    private EONPFRepository eonpfRepository;
    @Autowired
    private EONLanguageRepository eonLanguageRepository;
    @Autowired
    private EONInterestRepository eonInterestRepository;
    @Autowired
    private EONProfessionalExpRepository eonProfessionalExpRepository;
    @Autowired
    private EONProfReferRepository eonProfReferRepository;
    @Autowired
    private EONDocProofRepository eonDocProofRepository;
    @Autowired
    private EONDeclQuestRepository eonDeclQuestRepository;
    @Autowired
    private EONCandDeclRepository eonCandDeclRepository;
    @Autowired
    private EONHRDeclarationRepository eonhrDeclarationRepository;
    @Autowired
    private EONOverallStatusRepository eonOverallStatusRepository;

    @Autowired
    private OnBoardingMapper onBoardingMapper;
    @Autowired
    UrlToMultipartFileConvert urlToMultipartFileConvert;
    @Autowired
    private ExecutorService onboardingExecutor;

    @Autowired
    private OnBoardingOperationsCandidates onBoardingOperationsCandidates;
    @Autowired
    private EmployeeOnboardingRepository employeeRepo;


    @Override
    public Mono<List<GeneralListCandidatesData>> fetchAllHRCompletedGeneralCandidatesDetails() {
        return onBoardingOperationsCandidates.overallHRCompletedCandIds()
                .flatMapMany(tuple -> {
                    String sessionId = tuple.getT1();
                    List<String> candIds = tuple.getT2();
                    return Flux.fromIterable(candIds)
                            .flatMap(candId -> fetchHRCompletedGeneralCandidateDetails(candId, sessionId)
                                    .doOnNext(dto -> System.out.println("Fetched DTO for candidate: " + candId)));
                })
                .collectList();
    }

    @Override
    public Mono<GeneralListCandidatesData> fetchHRCompletedGeneralCandidateDetails(String candId, String sessionId) {
        // Fetch overall status first
        Mono<EONOverallStatusReqDTO> overallStatusMono = onBoardingOperationsCandidates.overallStatusDataOfSingleCandidates(candId, sessionId)
                .onErrorResume(ex -> Mono.empty()); // Return empty Mono on error

        // Fetch general data, using overallStatusMono as a trigger
        return overallStatusMono
                .flatMap(overallStatusData -> {
                    // We have overall status, now fetch general data in parallel with a just-in-time value.
                    Mono<EONGeneralReqDTO> generalMono = onBoardingOperationsCandidates.fetchEonGeneralData(candId, sessionId)
                            .onErrorResume(ex -> Mono.empty());

                    return Mono.zip(Mono.just(overallStatusData), generalMono)
                            .map(tuple -> {
                                GeneralListCandidatesData dto = new GeneralListCandidatesData();
                                dto.setCandId(candId);
                                dto.setEonOverallStatusReqDTO(tuple.getT1());
                                dto.setEonGeneralReqDTO(tuple.getT2());
                                return dto;
                            });
                })
                .switchIfEmpty(Mono.defer(() -> {
                    // If overallStatusMono was empty, fetch only general data
                    return onBoardingOperationsCandidates.fetchEonGeneralData(candId, sessionId)
                            .map(generalData -> {
                                GeneralListCandidatesData dto = new GeneralListCandidatesData();
                                dto.setCandId(candId);
                                dto.setEonGeneralReqDTO(generalData);
                                return dto;
                            })
                            .switchIfEmpty(Mono.just(new GeneralListCandidatesData())); // Return an empty DTO if both fail
                }))
                .doOnError(throwable -> System.err.println("An error occurred in the combined stream: " + throwable.getMessage()));
    }

//    @Override
//    public Mono<GeneralListCandidatesData> fetchHRCompletedGeneralCandidateDetails(String candId, String sessionId) {
//        Mono<EONGeneralReqDTO> general = onBoardingOperationsCandidates.fetchEonGeneralData(candId, sessionId)
//                .onErrorResume(ex -> {
//                    System.err.println("Failed to fetch general data for " + candId + ": " + ex.getMessage());
//                    return Mono.just( EONGeneralReqDTO.empty());
//                })
//                .defaultIfEmpty( EONGeneralReqDTO.empty());
//
//        Mono<EONOverallStatusReqDTO> overallstatusCandidatesdata = onBoardingOperationsCandidates.overallStatusDataOfSingleCandidates(candId, sessionId)
//                .onErrorResume(ex -> {
//                    System.err.println("Failed to fetch overallstatuscandidatesdata data for " + candId + ": " + ex.getMessage());
//                    return Mono.just( EONOverallStatusReqDTO.empty());
//                })
//                .defaultIfEmpty( EONOverallStatusReqDTO.empty());
//
//        return Mono.zip(general, overallstatusCandidatesdata)
//                .map(tuple -> {
//                    GeneralListCandidatesData dto = new GeneralListCandidatesData();
//                    dto.setCandId(candId);
//                    dto.setEonGeneralReqDTO(tuple.getT1());
//                    dto.setEonOverallStatusReqDTO(tuple.getT2());
//                    return dto;
//                });
//    }

    @Override
    public Mono<List<CandidateFullDetailsDTO>> fetchAllHRCompletedCandidatesDetails() {
        return onBoardingOperationsCandidates.overallHRCompletedCandIds()
                .flatMapMany(tuple -> {
                    String sessionId = tuple.getT1();
                    List<String> candIds = tuple.getT2();
                    return Flux.fromIterable(candIds)
                            .flatMap(candId -> fetchCandidateDetailsReactive(candId, sessionId)
                                    .doOnNext(dto -> System.out.println("Fetched DTO for candidate: " + candId)));
                })
                .collectList();
    }



    @Override
    public Mono<CandidateFullDetailsDTO> fetchCandidateDetailsReactive(String candId, String sessionId) {
        Mono<EONGeneralReqDTO> general = onBoardingOperationsCandidates.fetchEonGeneralData(candId, sessionId)
                .onErrorResume(ex -> {
                    System.err.println("Failed to fetch general data for " + candId + ": " + ex.getMessage());
                    return Mono.just( EONGeneralReqDTO.empty());
                })
                .defaultIfEmpty( EONGeneralReqDTO.empty());

        Mono<EONPersonalReqDTO> personal = Mono.fromFuture(
                onBoardingOperationsCandidates.fetchEonPersonalData(candId, sessionId)
                        .exceptionally(ex -> {
                            System.err.println("Failed to fetch personal data for " + candId + ": " + ex.getMessage());
                            return EONPersonalReqDTO.empty(); // Return fallback DTO
                        })
        ).defaultIfEmpty(EONPersonalReqDTO.empty());



        Mono<EONEducationReqDTO> education = onBoardingOperationsCandidates.fetchEonEducationData(candId, sessionId)
                .onErrorResume(ex -> {
                    System.err.println("Failed to fetch education data for " + candId + ": " + ex.getMessage());
                    return Mono.just(EONEducationReqDTO.empty());
                })
                .defaultIfEmpty( EONEducationReqDTO.empty());

        Mono<EONSkillReqDTO> skill = onBoardingOperationsCandidates.fetchEonSkillData(candId, sessionId)
                .onErrorResume(ex -> {
                    System.err.println("Failed to fetch skill data for " + candId + ": " + ex.getMessage());
                    return Mono.just( EONSkillReqDTO.empty());
                })
                .defaultIfEmpty( EONSkillReqDTO.empty());

        Mono<EONFamilyReqDTO> family = onBoardingOperationsCandidates.fetchEonFamilyData(candId,sessionId)
                .onErrorResume(ex -> {
                    System.err.println("Failed to fetch family data for " + candId + ": " + ex.getMessage());
                    return Mono.just( EONFamilyReqDTO.empty());
                })
                .defaultIfEmpty( EONFamilyReqDTO.empty());

        Mono<EONBankReqDTO> bank = onBoardingOperationsCandidates.fetchEonBankData(candId,sessionId)
                .onErrorResume(ex -> {
                    System.err.println("Failed to fetch bank data for " + candId + ": " + ex.getMessage());
                    return Mono.just( EONBankReqDTO.empty());
                })
                .defaultIfEmpty( EONBankReqDTO.empty());

        Mono<EONPFReqDTO> pf = onBoardingOperationsCandidates.fetchEonPfData(candId,sessionId)
                .onErrorResume(ex -> {
                    System.err.println("Failed to fetch pf data for " + candId + ": " + ex.getMessage());
                    return Mono.just( EONPFReqDTO.empty());
                })
                .defaultIfEmpty( EONPFReqDTO.empty());

        Mono<EONLanguageReqDTO> language = onBoardingOperationsCandidates.fetchEonLangData(candId,sessionId)
                .onErrorResume(ex -> {
                    System.err.println("Failed to fetch language data for " + candId + ": " + ex.getMessage());
                    return Mono.just( EONLanguageReqDTO.empty());
                })
                .defaultIfEmpty( EONLanguageReqDTO.empty());

        Mono<EONInterestReqDTO> interest = onBoardingOperationsCandidates.fetchEonInterestData(candId,sessionId)
                .onErrorResume(ex -> {
                    System.err.println("Failed to fetch interest data for " + candId + ": " + ex.getMessage());
                    return Mono.just( EONInterestReqDTO.empty());
                })
                .defaultIfEmpty( EONInterestReqDTO.empty());

        Mono<EONProfExpReqDTO> profExp = onBoardingOperationsCandidates.fetchEonProfExpData(candId,sessionId)
                .onErrorResume(ex -> {
                    System.err.println("Failed to fetch profexp data for " + candId + ": " + ex.getMessage());
                    return Mono.just( EONProfExpReqDTO.empty());
                })
                .defaultIfEmpty( EONProfExpReqDTO.empty());

        Mono<EONProfRefReqDTO> profRef = onBoardingOperationsCandidates.fetchEonProfRefData(candId,sessionId)
                .onErrorResume(ex -> {
                    System.err.println("Failed to fetch profref data for " + candId + ": " + ex.getMessage());
                    return Mono.just( EONProfRefReqDTO.empty());
                })
                .defaultIfEmpty( EONProfRefReqDTO.empty());

        Mono<EONDocsProofReqDTO> docsProof = Mono.fromFuture(onBoardingOperationsCandidates.fetchEonDocsProofData(candId,sessionId)
                .exceptionally(ex -> {
                    System.err.println("Failed to fetch docsproof data for " + candId + ": " + ex.getMessage());
                    return EONDocsProofReqDTO.empty(); // Return fallback DTO
                })
        ).defaultIfEmpty(EONDocsProofReqDTO.empty());

        Mono<EONDeclQnrReqDTO> declQnr = onBoardingOperationsCandidates.fetchEonDeclQnrData(candId, sessionId)
                .onErrorResume(ex -> {
                    System.err.println("Failed to fetch declqnr data for " + candId + ": " + ex.getMessage());
                    return Mono.just( EONDeclQnrReqDTO.empty());
                })
                .defaultIfEmpty( EONDeclQnrReqDTO.empty());

        Mono<EONCandDeclReqDTO> candDeclr = onBoardingOperationsCandidates.fetchEonCandDeclarationData(candId, sessionId)
                .onErrorResume(ex -> {
                    System.err.println("Failed to fetch candidatesdeclr data for " + candId + ": " + ex.getMessage());
                    return Mono.just( EONCandDeclReqDTO.empty());
                })
                .defaultIfEmpty( EONCandDeclReqDTO.empty());

        Mono<EONHRDeclReqDTO> hrDeclr = onBoardingOperationsCandidates.fetchEonHRDeclarationData(candId, sessionId)
                .onErrorResume(ex -> {
                    System.err.println("Failed to fetch hrdeclr data for " + candId + ": " + ex.getMessage());
                    return Mono.just( EONHRDeclReqDTO.empty());
                })
                .defaultIfEmpty( EONHRDeclReqDTO.empty());

        Mono<EONOverallStatusReqDTO> overallstatusCandidatesdata = onBoardingOperationsCandidates.overallStatusDataOfSingleCandidates(candId, sessionId)
                .onErrorResume(ex -> {
                    System.err.println("Failed to fetch overallstatuscandidatesdata data for " + candId + ": " + ex.getMessage());
                    return Mono.just( EONOverallStatusReqDTO.empty());
                })
                .defaultIfEmpty( EONOverallStatusReqDTO.empty());


        //        // Combine into a list of Monos
        List<Mono<?>> monos = Arrays.asList(
                general, personal, education, skill, family, bank, pf, language, interest,
                profExp, profRef, docsProof, declQnr, candDeclr, hrDeclr,overallstatusCandidatesdata
        );

        // Use Mono.zip with array of Monos
        return Mono.zip(monos, results -> {
            CandidateFullDetailsDTO dto = new CandidateFullDetailsDTO();
            dto.setCandId(candId);
            dto.setEonGeneralReqDTO((EONGeneralReqDTO) results[0]);
            dto.setEonPersonalReqDTO((EONPersonalReqDTO) results[1]);
            dto.setEonEducationReqDTO((EONEducationReqDTO) results[2]);
            dto.setEonSkillReqDTO((EONSkillReqDTO) results[3]);
            dto.setEonFamilyReqDTO((EONFamilyReqDTO) results[4]);
            dto.setEonBankReqDTO((EONBankReqDTO) results[5]);
            dto.setEonpfReqDTO((EONPFReqDTO) results[6]);
            dto.setEonLanguageReqDTO((EONLanguageReqDTO) results[7]);
            dto.setEonInterestReqDTO((EONInterestReqDTO) results[8]);
            dto.setEonProfExpReqDTO((EONProfExpReqDTO) results[9]);
            dto.setEonProfRefReqDTO((EONProfRefReqDTO) results[10]);
            dto.setEonDocsProofReqDTO((EONDocsProofReqDTO) results[11]);
            dto.setEonDeclQnrReqDTO((EONDeclQnrReqDTO) results[12]);
            dto.setEonCandDeclReqDTO((EONCandDeclReqDTO) results[13]);
            dto.setEonhrDeclReqDTO((EONHRDeclReqDTO) results[14]);
            dto.setEonOverallStatusReqDTO((EONOverallStatusReqDTO) results[15]);
            return dto;
        });
    }

    @Override
    public void saveOnBoardingCandidatesDetails(CandidateFullDetailsDTO dto) throws Exception {
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        if (dto.getEonPersonalReqDTO() != null) {
            // eonPersonalRepository.save(onBoardingMapper.toPersonalEntity(dto.getEonPersonalReqDTO()));
            EONPersonal eonPerosnalData = onBoardingMapper.toEntity(dto.getEonPersonalReqDTO());
            System.out.println("eon personal request data===" + eonPerosnalData);
            if (eonPerosnalData != null) {
                List<DocumentDTO> personaldto = urlToMultipartFileConvert.handleFileForwarding(dto.getEonPersonalReqDTO().filePhotoImage().get(0), dto.getEonPersonalReqDTO().filePhotoImage().get(1), dto.getEonPersonalReqDTO().candID());
                Long docId = personaldto.get(0).getDocId();
                eonPerosnalData.setFilePhotoImageDocID(docId);
                eonPersonalRepository.save(eonPerosnalData);
            }
        }

        if (dto.getEonGeneralReqDTO() != null) {
            futures.add(CompletableFuture.runAsync(() -> {
                eonGeneralRepository.save(onBoardingMapper.toGeneralEntity(dto.getEonGeneralReqDTO()));
            }, onboardingExecutor));
        }

        if (dto.getEonEducationReqDTO() != null) {
            futures.add(CompletableFuture.runAsync(() -> {
                eonEducationRepository.save(onBoardingMapper.toEducationEntity(dto.getEonEducationReqDTO()));
            }, onboardingExecutor));
        }

        if (dto.getEonSkillReqDTO() != null) {
            futures.add(CompletableFuture.runAsync(() -> {
                eonSkillRepository.save(onBoardingMapper.toSkillEntity(dto.getEonSkillReqDTO()));
            }, onboardingExecutor));
        }

        if (dto.getEonFamilyReqDTO() != null) {
            futures.add(CompletableFuture.runAsync(() -> {
                eonFamilyRepository.save(onBoardingMapper.toFamilyEntity(dto.getEonFamilyReqDTO()));
            }, onboardingExecutor));
        }

        if (dto.getEonBankReqDTO() != null) {
            futures.add(CompletableFuture.runAsync(() -> {
                eonBankRepository.save(onBoardingMapper.toBankEntity(dto.getEonBankReqDTO()));
            }, onboardingExecutor));
        }

        if (dto.getEonpfReqDTO() != null) {
            futures.add(CompletableFuture.runAsync(() -> {
                eonpfRepository.save(onBoardingMapper.toPFEntity(dto.getEonpfReqDTO()));
            }, onboardingExecutor));
        }

        if (dto.getEonLanguageReqDTO() != null) {
            futures.add(CompletableFuture.runAsync(() -> {
                eonLanguageRepository.save(onBoardingMapper.toLanguageEntity(dto.getEonLanguageReqDTO()));
            }, onboardingExecutor));
        }

        if (dto.getEonInterestReqDTO() != null) {
            futures.add(CompletableFuture.runAsync(() -> {
                eonInterestRepository.save(onBoardingMapper.toInterestEntity(dto.getEonInterestReqDTO()));
            }, onboardingExecutor));
        }

        if (dto.getEonProfExpReqDTO() != null) {
            futures.add(CompletableFuture.runAsync(() -> {
                eonProfessionalExpRepository.save(onBoardingMapper.toProfExpEntity(dto.getEonProfExpReqDTO()));
            }, onboardingExecutor));
        }

        if (dto.getEonProfRefReqDTO() != null) {
            futures.add(CompletableFuture.runAsync(() -> {
                eonProfReferRepository.save(onBoardingMapper.toProfRefEntity(dto.getEonProfRefReqDTO()));
            }, onboardingExecutor));
        }

        if (dto.getEonDocsProofReqDTO() != null) {
            // eonDocProofRepository.save(onBoardingMapper.toDocsProofEntity(dto.getEonDocsProofReqDTO()));


            EONDocumentProof eonDocumentProofdata = onBoardingMapper.toEonDocsProofEntity(dto.getEonDocsProofReqDTO());

            if (eonDocumentProofdata != null) {
                String candId = dto.getEonDocsProofReqDTO().candID();

                // Use the helper method to handle each document
                eonDocumentProofdata.setAadharDocId(handleDocument(dto.getEonDocsProofReqDTO().fileAadhar(), candId));
                eonDocumentProofdata.setFilePANDocId(handleDocument(dto.getEonDocsProofReqDTO().filePAN(), candId));
                eonDocumentProofdata.setFileVoterDocId(handleDocument(dto.getEonDocsProofReqDTO().fileVoter(), candId));
                eonDocumentProofdata.setFileAddrProof1DocId(handleDocument(dto.getEonDocsProofReqDTO().fileAddrProof1(), candId));
                eonDocumentProofdata.setFileDrivingDocId(handleDocument(dto.getEonDocsProofReqDTO().fileDriving(), candId));
                eonDocumentProofdata.setFileEmpDocId(handleDocument(dto.getEonDocsProofReqDTO().fileEmp(), candId));
                eonDocumentProofdata.setFilePassportDocId(handleDocument(dto.getEonDocsProofReqDTO().filePassport(), candId));
                eonDocumentProofdata.setFileAddrProof2DocId(handleDocument(dto.getEonDocsProofReqDTO().fileAddrProof2(), candId));
                eonDocumentProofdata.setFileAddrProof3DocId(handleDocument(dto.getEonDocsProofReqDTO().fileAddrProof3(), candId));

                // Save and return response DTO
                //onBoardingMapper.toDocsProofEntity(eonDocProofRepository.save(eonDocumentProofdata));
                eonDocProofRepository.save(eonDocumentProofdata);

            }
        }

        if (dto.getEonDeclQnrReqDTO() != null) {
            futures.add(CompletableFuture.runAsync(() -> {
                eonDeclQuestRepository.save(onBoardingMapper.toDeclQnrEntity(dto.getEonDeclQnrReqDTO()));
            }, onboardingExecutor));
        }

        if (dto.getEonCandDeclReqDTO() != null) {
            futures.add(CompletableFuture.runAsync(() -> {
                eonCandDeclRepository.save(onBoardingMapper.toCandDeclEntity(dto.getEonCandDeclReqDTO()));
            }, onboardingExecutor));
        }

        if (dto.getEonhrDeclReqDTO() != null) {
            futures.add(CompletableFuture.runAsync(() -> {
                eonhrDeclarationRepository.save(onBoardingMapper.toHRDeclEntity(dto.getEonhrDeclReqDTO()));
            }, onboardingExecutor));
        }

        if (dto.getEonOverallStatusReqDTO() != null) {
            futures.add(CompletableFuture.runAsync(() -> {
                dto.getEonOverallStatusReqDTO().setOverallStatus("Initiated");
                eonOverallStatusRepository.save(onBoardingMapper.toOverallStatusEntity(dto.getEonOverallStatusReqDTO()));
            }, onboardingExecutor));
        }
        CompletableFuture
                .allOf(futures.toArray(new CompletableFuture[0]))
                .join();
    }
//@Override
//    public void saveOnBoardingCandidatesDetails(CandidateFullDetailsDTO dto) throws Exception {
//        List<CompletableFuture<Void>> futures = new ArrayList<>();
//
//        if (dto.getEonPersonalReqDTO() != null) {
//            // eonPersonalRepository.save(onBoardingMapper.toPersonalEntity(dto.getEonPersonalReqDTO()));
//            EONPersonal eonPerosnalData = onBoardingMapper.toEntity(dto.getEonPersonalReqDTO());
//            System.out.println("eon personal request data===" + eonPerosnalData);
//            if (eonPerosnalData != null) {
//                List<DocumentDTO> personaldto = urlToMultipartFileConvert.handleFileForwarding(dto.getEonPersonalReqDTO().filePhotoImage().get(0), dto.getEonPersonalReqDTO().filePhotoImage().get(1), dto.getEonPersonalReqDTO().candID());
//                Long docId = personaldto.get(0).getDocId();
//                eonPerosnalData.setFilePhotoImageDocID(docId);
//                eonPersonalRepository.save(eonPerosnalData);
//            }
//        }
//
//        if (dto.getEonGeneralReqDTO() != null) {
//        eonGeneralRepository.save(onBoardingMapper.toGeneralEntity(dto.getEonGeneralReqDTO()));
//        }
//
//        if (dto.getEonEducationReqDTO() != null) {
//            eonEducationRepository.save(onBoardingMapper.toEducationEntity(dto.getEonEducationReqDTO()));
//        }
//
//        if (dto.getEonSkillReqDTO() != null) {
//            eonSkillRepository.save(onBoardingMapper.toSkillEntity(dto.getEonSkillReqDTO()));
//        }
//
//        if (dto.getEonFamilyReqDTO() != null) {
//            eonFamilyRepository.save(onBoardingMapper.toFamilyEntity(dto.getEonFamilyReqDTO()));
//        }
//
//        if (dto.getEonBankReqDTO() != null) {
//            eonBankRepository.save(onBoardingMapper.toBankEntity(dto.getEonBankReqDTO()));
//        }
//
//        if (dto.getEonpfReqDTO() != null) {
//            eonpfRepository.save(onBoardingMapper.toPFEntity(dto.getEonpfReqDTO()));
//        }
//
//        if (dto.getEonLanguageReqDTO() != null) {
//            eonLanguageRepository.save(onBoardingMapper.toLanguageEntity(dto.getEonLanguageReqDTO()));
//        }
//
//        if (dto.getEonInterestReqDTO() != null) {
//            eonInterestRepository.save(onBoardingMapper.toInterestEntity(dto.getEonInterestReqDTO()));
//        }
//
//        if (dto.getEonProfExpReqDTO() != null) {
//            eonProfessionalExpRepository.save(onBoardingMapper.toProfExpEntity(dto.getEonProfExpReqDTO()));
//        }
//
//        if (dto.getEonProfRefReqDTO() != null) {
//            eonProfReferRepository.save(onBoardingMapper.toProfRefEntity(dto.getEonProfRefReqDTO()));
//        }
//
//        if (dto.getEonDocsProofReqDTO() != null) {
//            // eonDocProofRepository.save(onBoardingMapper.toDocsProofEntity(dto.getEonDocsProofReqDTO()));
//
//
//            EONDocumentProof eonDocumentProofdata = onBoardingMapper.toEonDocsProofEntity(dto.getEonDocsProofReqDTO());
//
//            if (eonDocumentProofdata != null) {
//                String candId = dto.getEonDocsProofReqDTO().candID();
//
//                // Use the helper method to handle each document
//                eonDocumentProofdata.setAadharDocId(handleDocument(dto.getEonDocsProofReqDTO().fileAadhar(), candId));
//                eonDocumentProofdata.setFilePANDocId(handleDocument(dto.getEonDocsProofReqDTO().filePAN(), candId));
//                eonDocumentProofdata.setFileVoterDocId(handleDocument(dto.getEonDocsProofReqDTO().fileVoter(), candId));
//                eonDocumentProofdata.setFileAddrProof1DocId(handleDocument(dto.getEonDocsProofReqDTO().fileAddrProof1(), candId));
//                eonDocumentProofdata.setFileDrivingDocId(handleDocument(dto.getEonDocsProofReqDTO().fileDriving(), candId));
//                eonDocumentProofdata.setFileEmpDocId(handleDocument(dto.getEonDocsProofReqDTO().fileEmp(), candId));
//                eonDocumentProofdata.setFilePassportDocId(handleDocument(dto.getEonDocsProofReqDTO().filePassport(), candId));
//                eonDocumentProofdata.setFileAddrProof2DocId(handleDocument(dto.getEonDocsProofReqDTO().fileAddrProof2(), candId));
//                eonDocumentProofdata.setFileAddrProof3DocId(handleDocument(dto.getEonDocsProofReqDTO().fileAddrProof3(), candId));
//
//                // Save and return response DTO
//                //onBoardingMapper.toDocsProofEntity(eonDocProofRepository.save(eonDocumentProofdata));
//                eonDocProofRepository.save(eonDocumentProofdata);
//
//            }
//        }
//
//        if (dto.getEonDeclQnrReqDTO() != null) {
//            eonDeclQuestRepository.save(onBoardingMapper.toDeclQnrEntity(dto.getEonDeclQnrReqDTO()));
//        }
//
//        if (dto.getEonCandDeclReqDTO() != null) {
//            eonCandDeclRepository.save(onBoardingMapper.toCandDeclEntity(dto.getEonCandDeclReqDTO()));
//        }
//
//        if (dto.getEonhrDeclReqDTO() != null) {
//            eonhrDeclarationRepository.save(onBoardingMapper.toHRDeclEntity(dto.getEonhrDeclReqDTO()));
//        }
//
//    if (dto.getEonOverallStatusReqDTO() != null) {
//        dto.getEonOverallStatusReqDTO().setOverallStatus("Initiated");
//        eonOverallStatusRepository.save(onBoardingMapper.toOverallStatusEntity(dto.getEonOverallStatusReqDTO()));
//    }
//    }

    //to save document id in dms//
    private Long handleDocument(List<String> fileUrls, String candId) throws Exception {
        if (fileUrls == null || fileUrls.size() < 2) {
            throw new IllegalArgumentException("Expected two file URLs, but got: " + fileUrls);
        }

        List<DocumentDTO> docs = urlToMultipartFileConvert.handleFileForwarding(fileUrls.get(0), fileUrls.get(1), candId);
        if (docs == null || docs.isEmpty()) {
            throw new IllegalStateException("Document forwarding failed or returned empty list");
        }

        return docs.get(0).getDocId();
    }
    //fetch data from local entity class///
    @Override
    public EONPersonal fetchEonPersonalData(String candId) {
        EONPersonal eonPersonalList= eonPersonalRepository.findByCandID(candId);
        return eonPersonalList;
    }

    @Override
    public EONBank fetchEonBankData(String candId) {
        EONBank eonBank=eonBankRepository.findByCandID(candId);
        return eonBank;
    }

    @Override
    public EONCandidateDeclaration fetchEonCandidateDeclarationData(String candId) {
        EONCandidateDeclaration eonCandidateDeclaration=eonCandDeclRepository.findByCandID(candId);
        return eonCandidateDeclaration;
    }

    @Override
    public EONDeclQuestionnaire fetchEonDeclQuestionnaireData(String candId) {
        EONDeclQuestionnaire eonDeclQuestionnaire=eonDeclQuestRepository.findByCandID(candId);
        return eonDeclQuestionnaire;
    }

    @Override
    public EONDocumentProof fetchEonDocumentProofData(String candId) {
        EONDocumentProof eonDocumentProof=eonDocProofRepository.findByCandID(candId);
        return eonDocumentProof;
    }

    @Override
    public EONEducation fetchEonEducationData(String candId) {
        EONEducation eonEducation=eonEducationRepository.findByCandID(candId);
        return eonEducation;
    }

    @Override
    public EONFamily fetchEonFamilyData(String candId) {
        EONFamily eonFamily=eonFamilyRepository.findByCandID(candId);
        return eonFamily;
    }

    @Override
    public EONGeneral fetchEonGeneralData(String candId) {
        EONGeneral eonGeneral=eonGeneralRepository.findByCandID(candId);
        return eonGeneral;
    }

    @Override
    public EONHRDeclaration fetchEonHRDeclarationData(String candId) {
        EONHRDeclaration eonhrDeclaration=eonhrDeclarationRepository.findByCandID(candId);
        return eonhrDeclaration;
    }

    @Override
    public EONInterest fetchEonInterestData(String candId) {
        EONInterest eonInterest=eonInterestRepository.findByCandID(candId);
        return eonInterest;
    }

    @Override
    public EONLanguage fetchEonLanguageData(String candId) {
        EONLanguage eonLanguage=eonLanguageRepository.findByCandID(candId);
        return eonLanguage;
    }

    @Override
    public EONOverallStatus fetchEonOverallStatusData(String candId) {
        EONOverallStatus eonOverallStatus=eonOverallStatusRepository.findByCandID(candId);
        return eonOverallStatus;
    }

    @Override
    public EONPF fetchEonPFData(String candId) {
        EONPF eonpf=eonpfRepository.findByCandID(candId);
        return eonpf;
    }

    @Override
    public EONProfessionalReference fetchEonProfessionalReferenceData(String candId) {
        EONProfessionalReference eonProfessionalReference=eonProfReferRepository.findByCandID(candId);
        return eonProfessionalReference;
    }

    @Override
    public EONProfressionalExperience fetchEonProfressionalExperienceData(String candId) {
        EONProfressionalExperience eonProfressionalExperience = eonProfessionalExpRepository.findByCandID(candId);
        return eonProfressionalExperience;
    }

    @Override
    public EONSkill fetchEonSkillData(String candId) {
        EONSkill eonSkill=eonSkillRepository.findByCandID(candId);
        return eonSkill;
    }



//    @Override
//    public EmployeeOnboardingInfo saveEmployeeDataInOnboardingTable(EmployeeOnboardingInfo onboardingInfo) {
//        // Assuming empOnboardingRefNum1 is unique for each candidate
//        Optional<EmployeeOnboardingInfo> existingRecord =
//                repository.findByEmpOnboardingRefNum(onboardingInfo.getEmpOnboardingRefNum());
//
////        if (existingRecord.isPresent()) {
////            EmployeeOnboardingInfo existing = existingRecord.get();
////
////            // Update only required fields
////            existing.setIsProbationApplicable(onboardingInfo.getIsProbationApplicable());
////            existing.setRptManagerName(onboardingInfo.getRptManagerName());
////            existing.setRptMgrEmployeeCode(onboardingInfo.getRptMgrEmployeeCode());
////            existing.setRptManagerEmailId(onboardingInfo.getRptManagerEmailId());
////            existing.setMlwfApplicability(onboardingInfo.getMlwfApplicability());
////            existing.setGmcApplicability(onboardingInfo.getGmcApplicability());
////            existing.setGtlApplicability(onboardingInfo.getGtlApplicability());
////            existing.setGpaApplicability(onboardingInfo.getGpaApplicability());
////            existing.setWcApplicability(onboardingInfo.getWcApplicability());
////            existing.setHiringHr(onboardingInfo.getHiringHr());
////            existing.setEeSubgroup(onboardingInfo.getEeSubgroup());
////            existing.setEmploymentStatus(onboardingInfo.getEmploymentStatus());
////            existing.setGrade(onboardingInfo.getGrade());
////            existing.setPayrollAreaType(onboardingInfo.getPayrollAreaType());
////            existing.setBuHead(onboardingInfo.getBuHead());
////            existing.setMainDepartment(onboardingInfo.getMainDepartment());
////            existing.setSubDepartment(onboardingInfo.getSubDepartment());
////            existing.setProject(onboardingInfo.getProject());
////            existing.setOrganisationCode(onboardingInfo.getOrganisationCode());
////            existing.setDesignation(onboardingInfo.getDesignation());
////            existing.setOrganisationHierarchy(onboardingInfo.getOrganisationHierarchy());
////            existing.setFullNameAsPerPAN(onboardingInfo.getFullNameAsPerPAN());
////            existing.setCurrentPfNumber(onboardingInfo.getCurrentPfNumber());
////            existing.setCurrentESICNumber(onboardingInfo.getCurrentESICNumber());
////            existing.setStatus(onboardingInfo.getStatus());
////            existing.setConfirmationDate(onboardingInfo.getConfirmationDate());
////            existing.setProbationPeriod(onboardingInfo.getProbationPeriod());
////            existing.setMaxProbationExtension(onboardingInfo.getMaxProbationExtension());
////            existing.setGender(onboardingInfo.getGender());
////
//        if (existingRecord.isPresent()) {
//            EmployeeOnboardingInfo existing = existingRecord.get();
//            onBoardingMapper.updateEmployeeOnboardingInfoFromDto(onboardingInfo, existing);
//            return repository.save(existing);
//
//        } else {
//            // Insert new
//            return repository.save(onboardingInfo);
//        }
//    }

    @Override
    public EmployeeOnboardingInfo saveEmployeeDataInOnboardingTable(EmployeeOnboardingInfo onboardingInfo) {
        // This is the implementation provided in the user's context
        Optional<EmployeeOnboardingInfo> existingRecord =
                employeeRepo.findByEmpOnboardingRefNum(onboardingInfo.getEmpOnboardingRefNum());

        if (existingRecord.isPresent()) {
            EmployeeOnboardingInfo existing = existingRecord.get();
            // Assuming onBoardingMapper.updateEmployeeOnboardingInfoFromDto correctly maps fields
            onBoardingMapper.updateEmployeeOnboardingInfoFromDto(onboardingInfo, existing);
            return employeeRepo.save(existing);
        } else {
            // Insert new
            return employeeRepo.save(onboardingInfo);
        }
    }


    @Override
    @Transactional
    public EmployeeDiretoryDetailsDTO saveCandidateDetails(String candId, EmployeeDiretoryDetailsDTO completeDto) {

        // ... (logic for EmployeeOnboardingInfo, Personal, and General remains correct, as it was fixed previously)

        // 1. EmployeeOnboardingInfo (Main Table)
        if (completeDto.getEmployeeOnboardingInfo() != null) {
            EmployeeOnboardingInfo existingInfo = employeeRepo.findByCandID(candId);
            EmployeeOnboardingInfo entityToSave;

            if (existingInfo != null) {
                // Found existing record: UPDATE
                // Copy fields from DTO to existing entity, ignoring 'id' and 'candID'
                BeanUtils.copyProperties(completeDto.getEmployeeOnboardingInfo(), existingInfo, "id", "candID");
                entityToSave = existingInfo;
            } else {
                // No existing record: INSERT
                entityToSave = completeDto.getEmployeeOnboardingInfo();
            }

            entityToSave.setCandID(candId);
            EmployeeOnboardingInfo savedInfo = employeeRepo.save(entityToSave);
            completeDto.setEmployeeOnboardingInfo(savedInfo);
        }

        // 2. Personal Info
        if (completeDto.getPersonal() != null) {
            EONPersonal existing = eonPersonalRepository.findByCandID(candId);
            EONPersonal entityToSave;

            if (existing != null) {
                BeanUtils.copyProperties(completeDto.getPersonal(), existing, "id", "candID");
                entityToSave = existing;
            } else {
                entityToSave = completeDto.getPersonal();
            }

            entityToSave.setCandID(candId);
            EONPersonal savedPersonal = eonPersonalRepository.save(entityToSave);
            completeDto.setPersonal(savedPersonal);
        }

        // 3. General Info
        if (completeDto.getGeneral() != null) {
            EONGeneral existing = eonGeneralRepository.findByCandID(candId);
            EONGeneral entityToSave;

            if (existing != null) {
                BeanUtils.copyProperties(completeDto.getGeneral(), existing, "id", "candID");
                entityToSave = existing;
            } else {
                entityToSave = completeDto.getGeneral();
            }

            entityToSave.setCandID(candId);
            EONGeneral savedGeneral = eonGeneralRepository.save(entityToSave);
            completeDto.setGeneral(savedGeneral);
        }

        // 4. Family Info
        if (completeDto.getFamily() != null) {
            EONFamily existing = eonFamilyRepository.findByCandID(candId);
            EONFamily entityToSave;

            if (existing != null) {
                // Found existing record: UPDATE
                // 1. Perform copy operation (returns void)
                BeanUtils.copyProperties(completeDto.getFamily(), existing, "id", "candID");
                // 2. Set entityToSave to the updated existing object
                entityToSave = existing;
            } else {
                // No existing record: INSERT
                entityToSave = completeDto.getFamily();
            }

            entityToSave.setCandID(candId);
            completeDto.setFamily(eonFamilyRepository.save(entityToSave));
        }

        // 5. Bank Info
        if (completeDto.getBank() != null) {
            EONBank existing = eonBankRepository.findByCandID(candId);
            EONBank entityToSave;

            if (existing != null) {
                BeanUtils.copyProperties(completeDto.getBank(), existing, "id", "candID");
                entityToSave = existing;
            } else {
                entityToSave = completeDto.getBank();
            }

            entityToSave.setCandID(candId);
            completeDto.setBank(eonBankRepository.save(entityToSave));
        }

        // 6. Statutory Details (PF)
        if (completeDto.getPf() != null) {
            EONPF existing = eonpfRepository.findByCandID(candId);
            EONPF entityToSave;

            if (existing != null) {
                BeanUtils.copyProperties(completeDto.getPf(), existing, "id", "candID");
                entityToSave = existing;
            } else {
                entityToSave = completeDto.getPf();
            }

            entityToSave.setCandID(candId);
            completeDto.setPf(eonpfRepository.save(entityToSave));
        }

        // 7. Skills
        if (completeDto.getSkill() != null) {
            EONSkill existing = eonSkillRepository.findByCandID(candId);
            EONSkill entityToSave;

            if (existing != null) {
                BeanUtils.copyProperties(completeDto.getSkill(), existing, "id", "candID");
                entityToSave = existing;
            } else {
                entityToSave = completeDto.getSkill();
            }

            entityToSave.setCandID(candId);
            completeDto.setSkill(eonSkillRepository.save(entityToSave));
        }

        // 8. Education
        if (completeDto.getEducation() != null) {
            EONEducation existing = eonEducationRepository.findByCandID(candId);
            EONEducation entityToSave;

            if (existing != null) {
                BeanUtils.copyProperties(completeDto.getEducation(), existing, "id", "candID");
                entityToSave = existing;
            } else {
                entityToSave = completeDto.getEducation();
            }

            entityToSave.setCandID(candId);
            completeDto.setEducation(eonEducationRepository.save(entityToSave));
        }

        // 9. Professional Experience
        if (completeDto.getProfExp() != null) {
            EONProfressionalExperience existing = eonProfessionalExpRepository.findByCandID(candId);
            EONProfressionalExperience entityToSave;

            if (existing != null) {
                BeanUtils.copyProperties(completeDto.getProfExp(), existing, "id", "candID");
                entityToSave = existing;
            } else {
                entityToSave = completeDto.getProfExp();
            }

            entityToSave.setCandID(candId);
            completeDto.setProfExp(eonProfessionalExpRepository.save(entityToSave));
        }

        // ... continue this corrected UPSERT pattern for all other remaining entities ...

        return completeDto;
    }


//    @Override
//    public EmployeeOnboardingInfo saveEmployeeDataInOnboardingTable(EmployeeOnboardingInfo onboardingInfo) {
//        return repository.save(onboardingInfo);
//    }

    @Override
    public List<EmployeeDiretoryDetailsDTO> getAllCandidates() {
        List<EmployeeOnboardingInfo> employees = employeeRepo.findAll();

        return employees.stream().map(emp -> {
            String candId = emp.getCandID();
            EmployeeDiretoryDetailsDTO dto = new EmployeeDiretoryDetailsDTO();
            dto.setCandId(candId);

            // Use the ternary operator to check for null and instantiate a new object if null

            // 1. EONGeneral
            EONGeneral general = eonGeneralRepository.findByCandID(candId);
            dto.setGeneral(general != null ? general : new EONGeneral());

            // 2. EONPersonal
            EONPersonal personal = eonPersonalRepository.findByCandID(candId);
            dto.setPersonal(personal != null ? personal : new EONPersonal());

            // 3. EONFamily
            EONFamily family = eonFamilyRepository.findByCandID(candId);
            dto.setFamily(family != null ? family : new EONFamily());

            // 4. EONBank
            EONBank bank = eonBankRepository.findByCandID(candId);
            dto.setBank(bank != null ? bank : new EONBank());

            // 5. EONCandidateDeclaration
            EONCandidateDeclaration candDecl = eonCandDeclRepository.findByCandID(candId);
            dto.setCandidateDeclaration(candDecl != null ? candDecl : new EONCandidateDeclaration());

            // 6. EONDeclQuestionnaire
            EONDeclQuestionnaire declQuest = eonDeclQuestRepository.findByCandID(candId);
            dto.setDeclQuestionnaire(declQuest != null ? declQuest : new EONDeclQuestionnaire());

            // 7. EONDocumentProof
            EONDocumentProof docProof = eonDocProofRepository.findByCandID(candId);
            dto.setDocumentProof(docProof != null ? docProof : new EONDocumentProof());

            // 8. EONEducation
            EONEducation education = eonEducationRepository.findByCandID(candId);
            dto.setEducation(education != null ? education : new EONEducation());

            // 9. EONHRDeclaration
            EONHRDeclaration hrDecl = eonhrDeclarationRepository.findByCandID(candId);
            dto.setHrDeclaration(hrDecl != null ? hrDecl : new EONHRDeclaration());

            // 10. EONInterest
            EONInterest interest = eonInterestRepository.findByCandID(candId);
            dto.setInterest(interest != null ? interest : new EONInterest());

            // 11. EONLanguage
            EONLanguage language = eonLanguageRepository.findByCandID(candId);
            dto.setLanguage(language != null ? language : new EONLanguage());

            // 12. EONOverallStatus
            EONOverallStatus overallStatus = eonOverallStatusRepository.findByCandID(candId);
            dto.setOverallStatus(overallStatus != null ? overallStatus : new EONOverallStatus());

            // 13. EONPF
            EONPF pf = eonpfRepository.findByCandID(candId);
            dto.setPf(pf != null ? pf : new EONPF());

            // 14. EONProfessionalReference
            EONProfessionalReference profRef = eonProfReferRepository.findByCandID(candId);
            dto.setProfReference(profRef != null ? profRef : new EONProfessionalReference());

            // 15. EONProfressionalExperience
            EONProfressionalExperience profExp = eonProfessionalExpRepository.findByCandID(candId);
            dto.setProfExp(profExp != null ? profExp : new EONProfressionalExperience());

            // 16. EONSkill
            EONSkill skill = eonSkillRepository.findByCandID(candId);
            dto.setSkill(skill != null ? skill : new EONSkill());

            // 17. EmployeeOnboardingInfo (Likely always exists, but for consistency)
            EmployeeOnboardingInfo onboardingInfo = employeeRepo.findByCandID(candId);
            dto.setEmployeeOnboardingInfo(onboardingInfo != null ? onboardingInfo : new EmployeeOnboardingInfo());

            return dto;
        }).collect(Collectors.toList());
    }

//@Override
//    public List<EmployeeDiretoryDetailsDTO> getAllCandidates() {
//        List<EmployeeOnboardingInfo> employees = employeeRepo.findAll();
//
//        return employees.stream().map(emp -> {
//            String candId = emp.getCandID();
//            EmployeeDiretoryDetailsDTO dto = new EmployeeDiretoryDetailsDTO();
//           dto.setCandId(candId);
//            dto.setGeneral(eonGeneralRepository.findByCandID(candId));
//            dto.setPersonal(eonPersonalRepository.findByCandID(candId));
//            dto.setFamily(eonFamilyRepository.findByCandID(candId));
//            dto.setBank(eonBankRepository.findByCandID(candId));
//            dto.setCandidateDeclaration(eonCandDeclRepository.findByCandID(candId));
//            dto.setDeclQuestionnaire(eonDeclQuestRepository.findByCandID(candId));
//            dto.setDocumentProof(eonDocProofRepository.findByCandID(candId));
//            dto.setEducation(eonEducationRepository.findByCandID(candId));
//            dto.setHrDeclaration(eonhrDeclarationRepository.findByCandID(candId));
//            dto.setInterest(eonInterestRepository.findByCandID(candId));
//            dto.setLanguage(eonLanguageRepository.findByCandID(candId));
//            dto.setOverallStatus(eonOverallStatusRepository.findByCandID(candId));
//            dto.setPf(eonpfRepository.findByCandID(candId));
//            dto.setProfReference(eonProfReferRepository.findByCandID(candId));
//            dto.setProfExp(eonProfessionalExpRepository.findByCandID(candId));
//            dto.setSkill(eonSkillRepository.findByCandID(candId));
//            dto.setEmployeeOnboardingInfo(employeeRepo.findByCandID(candId));
//            return dto;
//        }).collect(Collectors.toList());
//    }

    @Override
    public EmployeeDiretoryDetailsDTO getCandidateDetailsById(String candId) {
        EmployeeOnboardingInfo emp = employeeRepo.findByCandID(candId);
        if (emp == null) return null;

        EmployeeDiretoryDetailsDTO dto = new EmployeeDiretoryDetailsDTO();
        dto.setCandId(candId);
        dto.setGeneral(eonGeneralRepository.findByCandID(candId));
        dto.setPersonal(eonPersonalRepository.findByCandID(candId));
        dto.setFamily(eonFamilyRepository.findByCandID(candId));
        dto.setBank(eonBankRepository.findByCandID(candId));
        dto.setCandidateDeclaration(eonCandDeclRepository.findByCandID(candId));
        dto.setDeclQuestionnaire(eonDeclQuestRepository.findByCandID(candId));
        dto.setDocumentProof(eonDocProofRepository.findByCandID(candId));
        dto.setEducation(eonEducationRepository.findByCandID(candId));
        dto.setHrDeclaration(eonhrDeclarationRepository.findByCandID(candId));
        dto.setInterest(eonInterestRepository.findByCandID(candId));
        dto.setLanguage(eonLanguageRepository.findByCandID(candId));
        dto.setOverallStatus(eonOverallStatusRepository.findByCandID(candId));
        dto.setPf(eonpfRepository.findByCandID(candId));
        dto.setProfReference(eonProfReferRepository.findByCandID(candId));
        dto.setProfExp(eonProfessionalExpRepository.findByCandID(candId));
        dto.setSkill(eonSkillRepository.findByCandID(candId));
        dto.setEmployeeOnboardingInfo(employeeRepo.findByCandID(candId));

        return dto;
    }


    @Override
    @Transactional
    public EmployeeDiretoryDetailsDTO updateCandidateDetails(String candId, EmployeeDiretoryDetailsDTO updatedDto) {

        // 1. EmployeeOnboardingInfo (Main Table)
        if (updatedDto.getEmployeeOnboardingInfo() != null) {
            EmployeeOnboardingInfo existingInfo = employeeRepo.findByCandID(candId);
            if (existingInfo != null) {
                // The candID from the path is used implicitly by fetching 'existingInfo'
                BeanUtils.copyProperties(updatedDto.getEmployeeOnboardingInfo(), existingInfo, "id", "candID");
                employeeRepo.save(existingInfo);
            }
        }

        // 2. Personal Info (Already Correct)
        if (updatedDto.getPersonal() != null) {
            EONPersonal existing = eonPersonalRepository.findByCandID(candId);
            if (existing != null) {
                BeanUtils.copyProperties(updatedDto.getPersonal(), existing, "id", "candID");
                eonPersonalRepository.save(existing);
            }
        }

        // 3. General Info (CORRECTION APPLIED)
        if (updatedDto.getGeneral() != null) {
            EONGeneral existing = eonGeneralRepository.findByCandID(candId);
            if (existing != null) {
                BeanUtils.copyProperties(updatedDto.getGeneral(), existing, "id", "candID");
                eonGeneralRepository.save(existing);
            }
        }

        // 4. Family Info (CORRECTION APPLIED)
        if (updatedDto.getFamily() != null) {
            EONFamily existing = eonFamilyRepository.findByCandID(candId);
            if (existing != null) {
                BeanUtils.copyProperties(updatedDto.getFamily(), existing, "id", "candID");
                eonFamilyRepository.save(existing);
            }
        }

        // 5. Bank Info (CORRECTION APPLIED)
        if (updatedDto.getBank() != null) {
            EONBank existing = eonBankRepository.findByCandID(candId);
            if (existing != null) {
                BeanUtils.copyProperties(updatedDto.getBank(), existing, "id", "candID");
                eonBankRepository.save(existing);
            }
        }

        // 6. Statutory Details (PF) (CORRECTION APPLIED)
        if (updatedDto.getPf() != null) {
            EONPF existing = eonpfRepository.findByCandID(candId);
            if (existing != null) {
                BeanUtils.copyProperties(updatedDto.getPf(), existing, "id", "candID");
                eonpfRepository.save(existing);
            }
        }

        // 7. Skills (CORRECTION APPLIED)
        if (updatedDto.getSkill() != null) {
            EONSkill existing = eonSkillRepository.findByCandID(candId);
            if (existing != null) {
                BeanUtils.copyProperties(updatedDto.getSkill(), existing, "id", "candID");
                eonSkillRepository.save(existing);
            }
        }

        // ... continue this pattern for all other sub-entities (Education, ProfExp, etc.)

        // 8. Education (CORRECTION APPLIED - Example)
        if (updatedDto.getEducation() != null) {
            EONEducation existing = eonEducationRepository.findByCandID(candId);
            if (existing != null) {
                BeanUtils.copyProperties(updatedDto.getEducation(), existing, "id", "candID");
                eonEducationRepository.save(existing);
            }
        }

        // 9. Professional Experience (CORRECTION APPLIED - Example)
        if (updatedDto.getProfExp() != null) {
            EONProfressionalExperience existing = eonProfessionalExpRepository.findByCandID(candId);
            if (existing != null) {
                BeanUtils.copyProperties(updatedDto.getProfExp(), existing, "id", "candID");
                eonProfessionalExpRepository.save(existing);
            }
        }

        // ... and so on for all remaining entities ...

        // 10. Return the updated DTO (or refetch the entity data)
        return updatedDto;
    }


    @Override
    public EmployeeOnboardingInfo getCandidateByCandId(String candID) {
        return  employeeRepo.findByCandID(candID);

    }
@Override
    public EmployeeOnboardingInfo updateCandidate(String candID, EmployeeOnboardingInfo updated) {
    EmployeeOnboardingInfo existing = getCandidateByCandId(candID);
        existing.setFullNameAsPerPAN(updated.getFullNameAsPerPAN());
        existing.setMainDepartment(updated.getMainDepartment());
        existing.setDesignation(updated.getDesignation());
        existing.setConfirmationDate(updated.getConfirmationDate());
        existing.setMainDepartment(updated.getMainDepartment());
        existing.setDesignation(updated.getDesignation());
        existing.setRptManagerName(updated.getRptManagerName());
        existing.setHiringHr(updated.getHiringHr());
        existing.setEmploymentStatus(updated.getEmploymentStatus());
    // Only update if the incoming payload has a value for it.
    if (updated.getExcelExportStatus() != null) {
        existing.setExcelExportStatus(updated.getExcelExportStatus());
    }
        return employeeRepo.save(existing);
    }

    @Override
    public void deleteCandidate(String candID) {
        employeeRepo.deleteByCandID(candID);
    }

    @Override
    public boolean deactivateCandidate(String candID) {
        EONOverallStatus status = eonOverallStatusRepository.findByCandID(candID);

        if (status == null) {
            return false;
        }

        status.setOverallStatus("InActive");
        eonOverallStatusRepository.save(status);

        return true;
    }

    // NEW: activateCandidate method
    @Override
    @Transactional
    public boolean activateCandidate(String candID) {
        EONOverallStatus status = eonOverallStatusRepository.findByCandID(candID);
        if (status == null) {
            return false; // Candidate not found
        }
        status.setOverallStatus("Active"); // Sets to Active
        eonOverallStatusRepository.save(status);
        return true;
    }


}
