package com.smalaca.archetypes.examples.organization;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.archetypes.examples.party.OrganizationUnit;

@ArchetypeParty.OrganizationUnit
public class Department extends OrganizationUnit {
    private final String departmentCode;

    public Department(String name, String departmentCode) {
        super(name);
        this.departmentCode = departmentCode;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }
}
