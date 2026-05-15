package com.smalaca.archetypes.party.organization;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.OrganizationUnit
public class Department extends Organization {
    private final DepartmentCode departmentCode;

    public Department(String name, DepartmentCode departmentCode) {
        super(name);
        this.departmentCode = departmentCode;
    }

    public DepartmentCode getDepartmentCode() {
        return departmentCode;
    }
}
