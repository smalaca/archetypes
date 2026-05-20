package com.smalaca.archetypes.partyrelationship.medium;

import org.assertj.core.api.AbstractAssert;

import java.util.UUID;

public class EmploymentAssert extends AbstractAssert<EmploymentAssert, Employment> {
    private EmploymentAssert(Employment actual) {
        super(actual, EmploymentAssert.class);
    }

    public static EmploymentAssert assertThat(Employment actual) {
        return new EmploymentAssert(actual);
    }

    public EmploymentAssert hasEmployer(UUID partyId) {
        isNotNull();
        if (!actual.getEmployer().getPartyId().equals(partyId)) {
            failWithMessage("Expected employer partyId to be <%s> but was <%s>", partyId, actual.getEmployer().getPartyId());
        }
        return this;
    }

    public EmploymentAssert hasEmployee(UUID partyId, String position) {
        isNotNull();
        boolean found = actual.getEmployees().stream()
                .anyMatch(role -> role.partyId().equals(partyId) && role.position().equals(position));
        if (!found) {
            failWithMessage("Expected employee with partyId <%s> and position <%s> to be present", partyId, position);
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
