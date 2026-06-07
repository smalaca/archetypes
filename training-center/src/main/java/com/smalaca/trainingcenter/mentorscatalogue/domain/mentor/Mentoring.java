package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.smalaca.trainingcenter.mentorscatalogue.domain.mentor.MentoringStatus.TO_BE_STARTED;

@DomainDrivenDesign.Entity
@ArchetypeParty.PartyRelationship
@ArchetypeParty.PartyRelationshipType
class Mentoring {
    private final MentoringId mentoringId;
    private final MentorId mentorId;
    private final List<MenteeId> mentees;
    private final MentoringStatus status;
    private final List<Note> notes = new ArrayList<>();

    private Mentoring(MentoringId mentoringId, MentorId mentorId, List<MenteeId> mentees, MentoringStatus status) {
        this.mentoringId = mentoringId;
        this.mentorId = mentorId;
        this.mentees = mentees;
        this.status = status;
    }

    @DomainDrivenDesign.Factory
    static Mentoring toBeStarted(MentorId mentorId, List<MenteeId> mentees) {
        MentoringId mentoringId = new MentoringId(UUID.randomUUID());

        return new Mentoring(mentoringId, mentorId, mentees, TO_BE_STARTED);
    }
}
