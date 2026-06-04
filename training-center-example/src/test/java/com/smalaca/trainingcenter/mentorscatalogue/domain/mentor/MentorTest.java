package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class MentorTest {
    private final SeniorityService seniorityService = mock(SeniorityService.class);
    private final CertificationService certificationService = mock(CertificationService.class);
    private final MenteeService menteeService = mock(MenteeService.class);
    private final MentorshipCapacityService capacityService = mock(MentorshipCapacityService.class);
    private final MentorFactory factory = MentorFactory.mentorFactory(seniorityService, certificationService, menteeService, capacityService);

    private final MentorId mentorId = new MentorId(UUID.randomUUID());
    private final UserId userId = new UserId(UUID.randomUUID());
    private final MentorNumber mentorNumber = new MentorNumber(UUID.randomUUID());
    private Mentor mentor;

    private final UUID menteeId = UUID.randomUUID();
    private final UUID topicId = UUID.randomUUID();
    private final MentoringContext context = new MentoringContext(menteeId, topicId, userId.id());

    @BeforeEach
    void createMentor() {
        given(seniorityService.hasEnoughExperience(userId)).willReturn(true);
        given(certificationService.isCertified(userId)).willReturn(true);
        mentor = factory.create(mentorId, userId, mentorNumber);
    }

    @Test
    void shouldInitiateMentoringWhenAllRulesAreSatisfied() {
        given(menteeService.canBeMentored(menteeId)).willReturn(true);
        given(capacityService.hasCapacity(userId.id())).willReturn(true);
        given(certificationService.isCertifiedFor(userId.id(), topicId)).willReturn(true);

        mentor.initiateMentoring(context);

        assertThat(mentor).extracting("initiatedMentoring").asList().containsExactly(menteeId);
    }

    @Test
    void shouldNotInitiateMentoringWhenMenteeEligibilityRuleIsNotSatisfied() {
        given(menteeService.canBeMentored(menteeId)).willReturn(false);
        given(capacityService.hasCapacity(userId.id())).willReturn(true);
        given(certificationService.isCertifiedFor(userId.id(), topicId)).willReturn(true);

        mentor.initiateMentoring(context);

        assertThat(mentor).extracting("initiatedMentoring").asList().isEmpty();
    }

    @Test
    void shouldNotInitiateMentoringWhenMentorshipCapacityRuleIsNotSatisfied() {
        given(menteeService.canBeMentored(menteeId)).willReturn(true);
        given(capacityService.hasCapacity(userId.id())).willReturn(false);
        given(certificationService.isCertifiedFor(userId.id(), topicId)).willReturn(true);

        mentor.initiateMentoring(context);

        assertThat(mentor).extracting("initiatedMentoring").asList().isEmpty();
    }

    @Test
    void shouldNotInitiateMentoringWhenMentorTopicCertificationRuleIsNotSatisfied() {
        given(menteeService.canBeMentored(menteeId)).willReturn(true);
        given(capacityService.hasCapacity(userId.id())).willReturn(true);
        given(certificationService.isCertifiedFor(userId.id(), topicId)).willReturn(false);

        mentor.initiateMentoring(context);

        assertThat(mentor).extracting("initiatedMentoring").asList().isEmpty();
    }
}
