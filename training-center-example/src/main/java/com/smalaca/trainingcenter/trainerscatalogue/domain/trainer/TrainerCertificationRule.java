package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Policy
@ArchetypeRule.Rule
class TrainerCertificationRule implements TrainingRule {
    private final CertificationService certificationService;

    TrainerCertificationRule(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @Override
    public TrainingAcceptanceResult isSatisfiedBy(TrainingContext context) {
        if (certificationService.isCertifiedFor(context.trainerUserId(), context.topicId(), context.levelId())) {
            return TrainingAcceptanceResult.accepted();
        }

        return TrainingAcceptanceResult.manualIntervention("Trainer certification missing.");
    }
}
