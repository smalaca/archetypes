package com.smalaca.archetypes.partyrelationship.medium;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

import java.util.ArrayList;
import java.util.List;

@ArchetypePartyRelationship.PartyRelationship
public class Employment {
    private final String employer;
    private final List<EmployeeRole> employees = new ArrayList<>();

    public Employment(String employer) {
        this.employer = employer;
    }

    public void addEmployee(String name, String position) {
        employees.add(new EmployeeRole(name, position));
    }

    public String getEmployer() {
        return employer;
    }

    public List<EmployeeRole> getEmployees() {
        return employees;
    }

    @ArchetypePartyRelationship.PartyRole
    public static record EmployeeRole(String name, String position) {
    }
}
