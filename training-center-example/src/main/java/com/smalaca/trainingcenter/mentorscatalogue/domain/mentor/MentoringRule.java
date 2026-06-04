package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

public interface MentoringRule {
    boolean isSatisfiedBy(MentoringContext context);
}
