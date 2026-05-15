package com.smalaca.archetypes.examples.organization;

import com.smalaca.archetypes.annotations.ArchetypeParty;

import java.util.ArrayList;
import java.util.List;

@ArchetypeParty.Organization
public class CorporateEntity {
    private final Organization organization;
    @ArchetypeParty.PartyIdentifier
    private final String taxIdentifier;

    private final List<Department> departments = new ArrayList<>();

    public CorporateEntity(String name, String taxIdentifier) {
        this.organization = new Organization(name);
        this.taxIdentifier = taxIdentifier;
    }

    public void addDepartment(Department department) {
        organization.addUnit(department.getOrganizationUnit());
        departments.add(department);
    }

    public Department getDepartment(String name) {
        return departments.stream()
                .filter(department -> department.getOrganizationUnit().getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public String getTaxIdentifier() {
        return taxIdentifier;
    }

    public Organization getOrganization() {
        return organization;
    }
}
