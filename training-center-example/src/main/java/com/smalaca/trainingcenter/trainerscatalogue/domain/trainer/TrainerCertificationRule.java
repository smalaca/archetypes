package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.archetypes.annotations.ArchetypeRule;

@ArchetypeRule.Rule
class TrainerCertificationRule implements TrainingRule {
    private final CertificationService certificationService;

    TrainerCertificationRule(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @Override
    public boolean isSatisfiedBy(TrainingContext context) {
        return certificationService.isCertifiedFor(context.trainerUserId(), context.topicId(), context.levelId());
    }
}
