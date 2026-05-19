package com.smalaca.archetypes.partyrelationship.medium;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

@ArchetypePartyRelationship.PartyRole
public record EmployeeRole(
        @ArchetypePartyRelationship.Party String name,
        @ArchetypePartyRelationship.RoleType String position) {
}
