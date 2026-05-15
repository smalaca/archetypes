package com.smalaca.archetypes.examples.organization;

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
