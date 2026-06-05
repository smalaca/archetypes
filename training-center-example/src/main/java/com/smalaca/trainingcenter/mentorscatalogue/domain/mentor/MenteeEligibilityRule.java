package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Specification
@ArchetypeRule.Rule
class MenteeEligibilityRule implements MentoringRule {
    private final MenteeService menteeService;

    MenteeEligibilityRule(MenteeService menteeService) {
        this.menteeService = menteeService;
    }

    @Override
    public boolean isSatisfiedBy(MentoringContext context) {
        return menteeService.canBeMentored(context.menteeId());
    }
}
