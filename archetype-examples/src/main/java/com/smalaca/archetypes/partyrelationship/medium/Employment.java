package com.smalaca.archetypes.partyrelationship.medium;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ArchetypePartyRelationship.PartyRelationship
public class Employment {
    @ArchetypePartyRelationship.PartyRole
    private final EmployerRole employer;
    @ArchetypePartyRelationship.PartyRole
    private final List<EmployeeRole> employees = new ArrayList<>();

    public Employment(UUID employerPartyId) {
        this.employer = new EmployerRole(employerPartyId);
    }

    public void addEmployee(UUID partyId, String position) {
        employees.add(new EmployeeRole(partyId, position));
    }

    public EmployerRole getEmployer() {
        return employer;
    }

    public List<EmployeeRole> getEmployees() {
        return employees;
    }
}
