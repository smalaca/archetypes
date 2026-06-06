package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Specification
interface MentoringCondition {
    boolean isSatisfiedBy(MentoringContext context);
}
