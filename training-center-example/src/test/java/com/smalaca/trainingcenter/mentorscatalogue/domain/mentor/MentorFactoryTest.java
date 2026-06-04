package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class MentorFactoryTest {
    private static final MentorId DUMMY_MENTOR_ID = new MentorId(UUID.randomUUID());
    private static final UserId DUMMY_USER_ID = new UserId(UUID.randomUUID());
    private static final MentorNumber DUMMY_MENTOR_NUMBER = new MentorNumber(UUID.randomUUID());

    private final SeniorityService seniorityService = mock(SeniorityService.class);
    private final CertificationService certificationService = mock(CertificationService.class);
    private final MenteeService menteeService = mock(MenteeService.class);
    private final MentorshipCapacityService capacityService = mock(MentorshipCapacityService.class);
    private final MentorFactory factory = MentorFactory.mentorFactory(seniorityService, certificationService, menteeService, capacityService);

    @Test
    void shouldCreateMentorWhenAllConstraintsAreSatisfied() {
        given(seniorityService.hasEnoughExperience(DUMMY_USER_ID)).willReturn(true);
        given(certificationService.isCertified(DUMMY_USER_ID)).willReturn(true);

        Mentor actual = factory.create(DUMMY_MENTOR_ID, DUMMY_USER_ID, DUMMY_MENTOR_NUMBER);

        assertThat(actual)
                .extracting("mentorId", "userId", "mentorNumber")
                .containsExactly(DUMMY_MENTOR_ID, DUMMY_USER_ID, DUMMY_MENTOR_NUMBER);
        assertThat(actual).extracting("rules").isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenExperienceSeniorityConstraintIsNotSatisfied() {
        given(seniorityService.hasEnoughExperience(DUMMY_USER_ID)).willReturn(false);

        assertThatThrownBy(() -> factory.create(DUMMY_MENTOR_ID, DUMMY_USER_ID, DUMMY_MENTOR_NUMBER))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Mentor constraints not satisfied");
    }

    @Test
    void shouldThrowExceptionWhenInternalCertificationConstraintIsNotSatisfied() {
        given(seniorityService.hasEnoughExperience(DUMMY_USER_ID)).willReturn(true);
        given(certificationService.isCertified(DUMMY_USER_ID)).willReturn(false);

        assertThatThrownBy(() -> factory.create(DUMMY_MENTOR_ID, DUMMY_USER_ID, DUMMY_MENTOR_NUMBER))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Mentor constraints not satisfied");
    }
}
