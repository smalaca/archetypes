package com.smalaca.archetypes.examples.organization;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.Organization
public class CorporateEntity {
    private final Organization organization;
    @ArchetypeParty.PartyIdentifier
    private final String taxIdentifier;

    public CorporateEntity(String name, String taxIdentifier) {
        this.organization = new Organization(name);
        this.taxIdentifier = taxIdentifier;
    }

    public void addDepartment(Department department) {
        organization.addUnit(department.getOrganizationUnit());
    }

    public String getTaxIdentifier() {
        return taxIdentifier;
    }

    public Organization getOrganization() {
        return organization;
    }
}
