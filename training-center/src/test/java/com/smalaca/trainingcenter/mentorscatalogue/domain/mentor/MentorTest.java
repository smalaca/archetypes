package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

import static com.smalaca.trainingcenter.mentorscatalogue.domain.mentor.MentoringStatus.TO_BE_STARTED;
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
    private final MentoringContext context = new MentoringContext(List.of(menteeId), userId.id());

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

        mentor.initiateMentoring(context);

        assertThat(mentor).extracting("mentorings").asList().hasSize(1);
        Mentoring mentoring = getMentoring(mentor, 0);
        assertThat(mentoring).extracting("mentorId").isEqualTo(mentorId);
        assertThat(mentoring).extracting("mentees").asList().containsExactly(new MenteeId(menteeId));
        assertThat(mentoring).extracting("status").isEqualTo(TO_BE_STARTED);
    }

    @Test
    void shouldNotInitiateMentoringWhenMenteeEligibilityRuleIsNotSatisfied() {
        given(menteeService.canBeMentored(menteeId)).willReturn(false);
        given(capacityService.hasCapacity(userId.id())).willReturn(true);

        mentor.initiateMentoring(context);

        assertThat(mentor).extracting("mentorings").asList().isEmpty();
    }

    @Test
    void shouldNotInitiateMentoringWhenMentorshipCapacityRuleIsNotSatisfied() {
        given(menteeService.canBeMentored(menteeId)).willReturn(true);
        given(capacityService.hasCapacity(userId.id())).willReturn(false);

        mentor.initiateMentoring(context);

        assertThat(mentor).extracting("mentorings").asList().isEmpty();
    }

    @Test
    void shouldInitiateAnotherMentoringWhenOneWasAlreadyInitiated() {
        UUID existingMenteeId = givenMentorWithInitiatedMentoring();
        given(menteeService.canBeMentored(menteeId)).willReturn(true);
        given(capacityService.hasCapacity(userId.id())).willReturn(true);

        mentor.initiateMentoring(context);

        assertThat(mentor).extracting("mentorings").asList().hasSize(2);
        Mentoring mentoringOne = getMentoring(mentor, 0);
        assertThat(mentoringOne).extracting("mentorId").isEqualTo(mentorId);
        assertThat(mentoringOne).extracting("mentees").asList().containsExactly(new MenteeId(existingMenteeId));
        assertThat(mentoringOne).extracting("status").isEqualTo(TO_BE_STARTED);
        Mentoring mentoringTwo = getMentoring(mentor, 1);
        assertThat(mentoringTwo).extracting("mentorId").isEqualTo(mentorId);
        assertThat(mentoringTwo).extracting("mentees").asList().containsExactly(new MenteeId(menteeId));
        assertThat(mentoringTwo).extracting("status").isEqualTo(TO_BE_STARTED);
    }

    @Test
    void shouldNotInitiateAnotherMentoringWhenRulesAreNotSatisfiedAndOneWasAlreadyInitiated() {
        UUID existingMenteeId = givenMentorWithInitiatedMentoring();
        given(menteeService.canBeMentored(menteeId)).willReturn(false);

        mentor.initiateMentoring(context);

        assertThat(mentor).extracting("mentorings").asList().hasSize(1);
        Mentoring mentoring = getMentoring(mentor, 0);
        assertThat(mentoring).extracting("mentorId").isEqualTo(mentorId);
        assertThat(mentoring).extracting("mentees").asList().containsExactly(new MenteeId(existingMenteeId));
        assertThat(mentoring).extracting("status").isEqualTo(TO_BE_STARTED);
    }

    @Test
    void shouldNotInitiateMentoringWhenTooManyMentees() {
        List<UUID> tooManyMentees = List.of(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        MentoringContext tooManyContext = new MentoringContext(tooManyMentees, userId.id());
        given(capacityService.hasCapacity(userId.id())).willReturn(true);
        tooManyMentees.forEach(id -> given(menteeService.canBeMentored(id)).willReturn(true));

        mentor.initiateMentoring(tooManyContext);

        assertThat(mentor).extracting("mentorings").asList().isEmpty();
    }

    @Test
    void shouldNotInitiateMentoringWhenNoMentees() {
        MentoringContext noMenteesContext = new MentoringContext(List.of(), userId.id());
        given(capacityService.hasCapacity(userId.id())).willReturn(true);

        mentor.initiateMentoring(noMenteesContext);

        assertThat(mentor).extracting("mentorings").asList().isEmpty();
    }

    private UUID givenMentorWithInitiatedMentoring() {
        UUID existingMenteeId = UUID.randomUUID();
        given(menteeService.canBeMentored(existingMenteeId)).willReturn(true);
        given(capacityService.hasCapacity(userId.id())).willReturn(true);
        mentor.initiateMentoring(new MentoringContext(List.of(existingMenteeId), userId.id()));

        return existingMenteeId;
    }

    private Mentoring getMentoring(Mentor mentor, int index) {
        try {
            Field field = Mentor.class.getDeclaredField("mentorings");
            field.setAccessible(true);
            return ((List<Mentoring>) field.get(mentor)).get(index);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
