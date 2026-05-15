package com.smalaca.archetypes.examples.organization;

import com.smalaca.archetypes.annotations.ArchetypeParty;

import java.util.ArrayList;
import java.util.List;

@ArchetypeParty.OrganizationUnit
public class Department {
    private final OrganizationUnit organizationUnit;
    @ArchetypeParty.PartyIdentifier
    private final String departmentCode;

    private final List<Department> subDepartments = new ArrayList<>();

    public Department(String name, String departmentCode) {
        this.organizationUnit = new OrganizationUnit(name);
        this.departmentCode = departmentCode;
    }

    public void addSubDepartment(Department department) {
        organizationUnit.addUnit(department.getOrganizationUnit());
        subDepartments.add(department);
    }

    public List<Department> getSubDepartments() {
        return List.copyOf(subDepartments);
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public OrganizationUnit getOrganizationUnit() {
        return organizationUnit;
    }
}
