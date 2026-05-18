package com.smalaca.archetypes.partyrelationship.full;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

@ArchetypePartyRelationship.RelationshipConstraint
public class OrganizationalHierarchyConstraint implements RelationshipConstraint {
    private final String requiredEmployerRole;
    private final String requiredEmployeeRole;

    public OrganizationalHierarchyConstraint(String requiredEmployerRole, String requiredEmployeeRole) {
        this.requiredEmployerRole = requiredEmployerRole;
        this.requiredEmployeeRole = requiredEmployeeRole;
    }

    @Override
    public boolean isSatisfiedBy(PartyRelationship relationship) {
        boolean hasEmployer = relationship.getRoles().stream()
                .anyMatch(role -> role.getRoleType().name().equals(requiredEmployerRole) && role.getParty() instanceof Organization);
        boolean hasEmployee = relationship.getRoles().stream()
                .anyMatch(role -> role.getRoleType().name().equals(requiredEmployeeRole) && role.getParty() instanceof Person);

        return hasEmployer && hasEmployee;
    }
}
