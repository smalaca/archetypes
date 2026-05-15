package com.smalaca.archetypes.examples.organization;

import org.assertj.core.api.AbstractAssert;

import java.util.Collection;

public class DepartmentAssert extends AbstractAssert<DepartmentAssert, Department> {
    private DepartmentAssert(Department actual) {
        super(actual, DepartmentAssert.class);
    }

    public static DepartmentAssert assertThat(Department actual) {
        return new DepartmentAssert(actual);
    }

    public DepartmentAssert hasDepartments(int expected) {
        isNotNull();

        Collection<Department> subUnits = actual.getDepartments();

        if (subUnits.size() != expected) {
            failWithMessage("Expected organization unit to have <%s> sub-units but was <%s>", expected, subUnits.size());
        }

        return this;
    }

    public DepartmentAssert hasDepartment(String name, String code) {
        isNotNull();

        Department subUnit = actual.getDepartment(name);

        if (subUnit == null) {
            failWithMessage("Expected organization unit to have sub-unit <%s> but was not found", name);
        }

        if (!subUnit.getDepartmentCode().value().equals(code)) {
            failWithMessage("Expected sub-unit <%s> to have code <%s> but was <%s>", name, code, subUnit.getDepartmentCode().value());
        }

        return this;
    }
}
