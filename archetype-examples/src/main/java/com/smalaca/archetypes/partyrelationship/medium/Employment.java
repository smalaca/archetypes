package com.smalaca.archetypes.partyrelationship.medium;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

import java.util.ArrayList;
import java.util.List;

@ArchetypePartyRelationship.PartyRelationship
public class Employment {
    @ArchetypePartyRelationship.RelationshipType
    private final String relationshipType = "Employment";
    @ArchetypePartyRelationship.PartyRole
    private final EmployerRole employer;
    @ArchetypePartyRelationship.PartyRole
    private final List<EmployeeRole> employees = new ArrayList<>();

    public Employment(String employerName) {
        this.employer = new EmployerRole(employerName);
    }

    public void addEmployee(String name, String position) {
        employees.add(new EmployeeRole(name, position));
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public EmployerRole getEmployer() {
        return employer;
    }

    public List<EmployeeRole> getEmployees() {
        return employees;
    }

    @ArchetypePartyRelationship.PartyRole
    public static class EmployerRole {
        @ArchetypePartyRelationship.RoleType
        private final String roleType = "Employer";
        @ArchetypePartyRelationship.Party
        private final String name;

        public EmployerRole(String name) {
            this.name = name;
        }

        public String getRoleType() {
            return roleType;
        }

        public String getName() {
            return name;
        }
    }

    @ArchetypePartyRelationship.PartyRole
    public static record EmployeeRole(
            @ArchetypePartyRelationship.Party String name,
            @ArchetypePartyRelationship.RoleType String position) {
    }
}
