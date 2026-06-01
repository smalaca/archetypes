package com.smalaca.trainingcenter.mentorscatalogue.model;

import com.smalaca.archetypes.annotations.ArchetypeParty;

import java.util.UUID;

@ArchetypeParty.PartyRoleIdentifier
public record MentorNumber(UUID id) {
}
