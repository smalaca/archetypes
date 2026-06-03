package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class MentorFactoryTest {
    private final MentorId mentorId = new MentorId(UUID.randomUUID());
    private final UserId userId = new UserId(UUID.randomUUID());
    private final MentorNumber mentorNumber = new MentorNumber(UUID.randomUUID());

    private final SeniorityService seniorityService = mock(SeniorityService.class);
    private final CertificationService certificationService = mock(CertificationService.class);

    private MentorFactory factory;

    @BeforeEach
    void setUp() {
        factory = new MentorFactory(List.of(
                new ExperienceSeniorityConstraint(seniorityService),
                new InternalCertificationConstraint(certificationService)
        ));
    }

    @Test
    void shouldCreateMentorWhenAllConstraintsAreSatisfied() {
        given(seniorityService.hasEnoughExperience(userId)).willReturn(true);
        given(certificationService.isCertified(userId)).willReturn(true);

        Mentor mentor = factory.create(mentorId, userId, mentorNumber);

        assertThat(mentor).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenExperienceSeniorityConstraintIsNotSatisfied() {
        given(seniorityService.hasEnoughExperience(userId)).willReturn(false);

        assertThatThrownBy(() -> factory.create(mentorId, userId, mentorNumber))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Mentor constraints not satisfied");
    }

    @Test
    void shouldThrowExceptionWhenInternalCertificationConstraintIsNotSatisfied() {
        given(seniorityService.hasEnoughExperience(userId)).willReturn(true);
        given(certificationService.isCertified(userId)).willReturn(false);

        assertThatThrownBy(() -> factory.create(mentorId, userId, mentorNumber))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Mentor constraints not satisfied");
    }
}
