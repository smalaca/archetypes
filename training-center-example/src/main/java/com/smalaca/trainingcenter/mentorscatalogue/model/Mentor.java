package com.smalaca.trainingcenter.mentorscatalogue.model;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRole
public class Mentor {
    private final MentorId mentorId;
    private final UserId userId;
    private final MentorNumber mentorNumber;

    public Mentor(MentorId mentorId, UserId userId, MentorNumber mentorNumber) {
        this.mentorId = mentorId;
        this.userId = userId;
        this.mentorNumber = mentorNumber;
    }
}
