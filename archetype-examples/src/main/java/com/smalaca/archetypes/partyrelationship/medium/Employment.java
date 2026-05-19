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

}
