package com.smalaca.archetypes.partyrelationship.medium;

import org.assertj.core.api.AbstractAssert;

public class EmploymentAssert extends AbstractAssert<EmploymentAssert, Employment> {
    private EmploymentAssert(Employment actual) {
        super(actual, EmploymentAssert.class);
    }

    public static EmploymentAssert assertThat(Employment actual) {
        return new EmploymentAssert(actual);
    }

    public EmploymentAssert hasRelationshipType(String type) {
        isNotNull();
        if (!actual.getRelationshipType().equals(type)) {
            failWithMessage("Expected relationship type to be <%s> but was <%s>", type, actual.getRelationshipType());
        }
        return this;
    }

    public EmploymentAssert hasEmployer(String employerName) {
        isNotNull();
        if (!actual.getEmployer().getName().equals(employerName)) {
            failWithMessage("Expected employer to be <%s> but was <%s>", employerName, actual.getEmployer().getName());
        }
        return this;
    }

    public EmploymentAssert hasEmployerRoleType(String roleType) {
        isNotNull();
        if (!actual.getEmployer().getRoleType().equals(roleType)) {
            failWithMessage("Expected employer role type to be <%s> but was <%s>", roleType, actual.getEmployer().getRoleType());
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
