package com.smalaca.archetypes.examples.organization;

import com.smalaca.archetypes.annotations.Archetype;
import com.smalaca.archetypes.annotations.ArchetypePart;
import com.smalaca.archetypes.party.OrganizationUnit;

@Archetype(name = "Party")
@ArchetypePart(archetype = "Party", part = "OrganizationUnit")
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
