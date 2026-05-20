package com.smalaca.archetypes.partyrelationship.medium;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

import java.util.UUID;

@ArchetypePartyRelationship.PartyRole
public record EmployeeRole(
        @ArchetypePartyRelationship.Party UUID partyId,
        @ArchetypePartyRelationship.RoleType String position) {
}
