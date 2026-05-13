package com.smalaca.archetypes.examples.organization;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.OrganizationUnit
public class Department {
    private final OrganizationUnit organizationUnit;
    private final String departmentCode;

    public Department(String name, String departmentCode) {
        this.organizationUnit = new OrganizationUnit(name);
        this.departmentCode = departmentCode;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public OrganizationUnit getOrganizationUnit() {
        return organizationUnit;
    }
}
