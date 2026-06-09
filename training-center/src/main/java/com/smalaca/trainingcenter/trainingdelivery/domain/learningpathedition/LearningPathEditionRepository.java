package com.smalaca.trainingcenter.trainingdelivery.domain.learningpathedition;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.annotations.architecture.PortsAndAdaptersArchitecture;
import com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession.TrainingSessionId;

@DomainDrivenDesign.Repository
@PortsAndAdaptersArchitecture.DrivenPort
public interface LearningPathEditionRepository {
    boolean existsAnyWith(TrainingSessionId trainingSessionId);
}
