package com.smalaca.archetypes.examples.organization;

import org.assertj.core.api.AbstractAssert;

import java.util.Collection;

public class CorporateEntityAssert extends AbstractAssert<CorporateEntityAssert, CorporateEntity> {
    private CorporateEntityAssert(CorporateEntity actual) {
        super(actual, CorporateEntityAssert.class);
    }

    public static CorporateEntityAssert assertThat(CorporateEntity actual) {
        return new CorporateEntityAssert(actual);
    }

    public CorporateEntityAssert hasName(String name) {
        isNotNull();

        if (!actual.getOrganization().getName().equals(name)) {
            failWithMessage("Expected corporate entity name to be <%s> but was <%s>", name, actual.getOrganization().getName());
        }

        return this;
    }

    public CorporateEntityAssert hasTaxIdentifier(String taxIdentifier) {
        isNotNull();

        if (!actual.getTaxIdentifier().equals(taxIdentifier)) {
            failWithMessage("Expected tax identifier to be <%s> but was <%s>", taxIdentifier, actual.getTaxIdentifier());
        }

        return this;
    }

    public CorporateEntityAssert hasDepartments(String... departmentNames) {
        isNotNull();

        Collection<OrganizationUnit> units = actual.getOrganization().getUnits();
        org.assertj.core.api.Assertions.assertThat(units)
                .extracting(OrganizationUnit::getName)
                .containsExactlyInAnyOrder(departmentNames);

        return this;
    }

    public CorporateEntityAssert hasDepartments(int expected) {
        isNotNull();

        Collection<OrganizationUnit> units = actual.getOrganization().getUnits();

        if (units.size() != expected) {
            failWithMessage("Expected corporate entity to have <%s> departments but was <%s>", expected, units.size());
        }

        return this;
    }

    public CorporateEntityAssert hasDepartment(String name, String code) {
        isNotNull();

        Department department = actual.getDepartment(name);

        if (department == null) {
            failWithMessage("Expected corporate entity to have department <%s> but was not found", name);
        }

        if (!department.getDepartmentCode().equals(code)) {
            failWithMessage("Expected department <%s> to have code <%s> but was <%s>", name, code, department.getDepartmentCode());
        }

        return this;
    }

    public CorporateEntityAssert hasSubDepartment(String parentName, String subDepartmentName) {
        isNotNull();

        OrganizationUnit parent = findUnitByName(actual.getOrganization().getUnits(), parentName);

        if (parent == null) {
            failWithMessage("Expected corporate entity to have department <%s> but was not found", parentName);
        }

        OrganizationUnit subUnit = findUnitByName(parent.getUnits(), subDepartmentName);

        if (subUnit == null) {
            failWithMessage("Expected department <%s> to have sub-department <%s> but was not found", parentName, subDepartmentName);
        }

        return this;
    }

    private OrganizationUnit findUnitByName(Collection<OrganizationUnit> units, String name) {
        return units.stream()
                .filter(unit -> unit.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
