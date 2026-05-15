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

        if (!actual.getName().equals(name)) {
            failWithMessage("Expected corporate entity name to be <%s> but was <%s>", name, actual.getName());
        }

        return this;
    }

    public CorporateEntityAssert hasTaxIdentifier(String taxIdentifier) {
        isNotNull();

        if (!actual.getTaxIdentifier().value().equals(taxIdentifier)) {
            failWithMessage("Expected tax identifier to be <%s> but was <%s>", taxIdentifier, actual.getTaxIdentifier().value());
        }

        return this;
    }

    public CorporateEntityAssert hasDepartments(String... departmentNames) {
        isNotNull();

        Collection<Department> units = actual.getDepartments();
        org.assertj.core.api.Assertions.assertThat(units)
                .extracting(Department::getName)
                .containsExactlyInAnyOrder(departmentNames);

        return this;
    }

    public CorporateEntityAssert hasDepartments(int expected) {
        isNotNull();

        Collection<Department> units = actual.getDepartments();

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

        if (!department.getDepartmentCode().value().equals(code)) {
            failWithMessage("Expected department <%s> to have code <%s> but was <%s>", name, code, department.getDepartmentCode().value());
        }

        return this;
    }

    public CorporateEntityAssert hasSubDepartment(String parentName, String subDepartmentName) {
        isNotNull();

        Department parent = findUnitByName(actual.getDepartments(), parentName);

        if (parent == null) {
            failWithMessage("Expected corporate entity to have department <%s> but was not found", parentName);
        }

        Department subUnit = findUnitByName(parent.getDepartments(), subDepartmentName);

        if (subUnit == null) {
            failWithMessage("Expected department <%s> to have sub-department <%s> but was not found", parentName, subDepartmentName);
        }

        return this;
    }

    private Department findUnitByName(Collection<Department> units, String name) {
        return units.stream()
                .filter(unit -> unit.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
