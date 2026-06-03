package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import java.util.List;

public class MentorFactory {
    private final List<MentorConstraint> constraints;

    private MentorFactory(List<MentorConstraint> constraints) {
        this.constraints = constraints;
    }

    public static MentorFactory mentorFactory(SeniorityService seniorityService, CertificationService certificationService) {
        return new MentorFactory(List.of(
                new ExperienceSeniorityConstraint(seniorityService),
                new InternalCertificationConstraint(certificationService)
        ));
    }

    public Mentor create(MentorId mentorId, UserId userId, MentorNumber mentorNumber) {
        if (isSatisfied(userId)) {
            return new Mentor(mentorId, userId, mentorNumber);
        }

        throw new RuntimeException("Mentor constraints not satisfied");
    }

    private boolean isSatisfied(UserId userId) {
        return constraints.stream().allMatch(constraint -> constraint.isSatisfiedBy(userId));
    }
}
