package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.archetypes.ArchetypeRule;

@ArchetypeRule.Rule
class MentorshipCapacityRule implements MentoringRule {
    private final MentorshipCapacityService capacityService;

    MentorshipCapacityRule(MentorshipCapacityService capacityService) {
        this.capacityService = capacityService;
    }

    @Override
    public boolean isSatisfiedBy(MentoringContext context) {
        return capacityService.hasCapacity(context.mentorId());
    }
}
