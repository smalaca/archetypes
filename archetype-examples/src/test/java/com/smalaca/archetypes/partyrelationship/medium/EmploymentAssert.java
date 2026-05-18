package com.smalaca.archetypes.partyrelationship.medium;

import org.assertj.core.api.AbstractAssert;
import java.util.List;

public class EmploymentAssert extends AbstractAssert<EmploymentAssert, Employment> {
    private EmploymentAssert(Employment actual) {
        super(actual, EmploymentAssert.class);
    }

    public static EmploymentAssert assertThat(Employment actual) {
        return new EmploymentAssert(actual);
    }

    public EmploymentAssert hasEmployer(String employer) {
        isNotNull();

        if (!actual.getEmployer().equals(employer)) {
            failWithMessage("Expected employer to be <%s> but was <%s>", employer, actual.getEmployer());
        }

        return this;
    }

    public EmploymentAssert hasEmployee(String name, String position) {
        isNotNull();

        boolean found = actual.getEmployees().stream()
                .anyMatch(role -> role.name().equals(name) && role.position().equals(position));

        if (!found) {
            failWithMessage("Expected employee <%s> with position <%s> to be present", name, position);
        }

        return this;
    }

    public EmploymentAssert hasEmployeesCount(int count) {
        isNotNull();

        if (actual.getEmployees().size() != count) {
            failWithMessage("Expected employees count to be <%d> but was <%d>", count, actual.getEmployees().size());
        }

        return this;
    }
}
