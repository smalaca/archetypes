package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import java.util.List;

@DomainDrivenDesign.Factory
public class MentorFactory {
    private final List<MentorConstraint> constraints;
    private final MenteeService menteeService;
    private final MentorshipCapacityService capacityService;
    private final CertificationService certificationService;

    private MentorFactory(
            List<MentorConstraint> constraints, MenteeService menteeService,
            MentorshipCapacityService capacityService, CertificationService certificationService) {
        this.constraints = constraints;
        this.menteeService = menteeService;
        this.capacityService = capacityService;
        this.certificationService = certificationService;
    }

    public static MentorFactory mentorFactory(
            SeniorityService seniorityService, CertificationService certificationService,
            MenteeService menteeService, MentorshipCapacityService capacityService) {
        return new MentorFactory(
                List.of(
                        new ExperienceSeniorityConstraint(seniorityService),
                        new InternalCertificationConstraint(certificationService)
                ),
                menteeService, capacityService, certificationService);
    }

    public Mentor create(MentorId mentorId, UserId userId, MentorNumber mentorNumber) {
        if (isSatisfied(userId)) {
            return new Mentor(mentorId, userId, mentorNumber, rules());
        }

        throw new RuntimeException("Mentor constraints not satisfied");
    }

    private List<MentoringCondition> rules() {
        return List.of(
                new RoleCompatibilityConstraint(),
                new MentorshipCapacityRule(capacityService),
                new MenteeEligibilityConstraint(menteeService)
        );
    }

    private boolean isSatisfied(UserId userId) {
        return constraints.stream().allMatch(constraint -> constraint.isSatisfiedBy(userId));
    }
}
