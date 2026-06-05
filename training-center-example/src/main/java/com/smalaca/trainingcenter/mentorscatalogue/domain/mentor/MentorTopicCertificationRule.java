package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Specification
@ArchetypeRule.Rule
class MentorTopicCertificationRule implements MentoringRule {
    private final CertificationService certificationService;

    MentorTopicCertificationRule(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @Override
    public boolean isSatisfiedBy(MentoringContext context) {
        return certificationService.isCertifiedFor(context.mentorId(), context.topicId());
    }
}
