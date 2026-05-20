package com.smalaca.archetypes.partyrelationship.simple;

import org.assertj.core.api.AbstractAssert;

import java.util.UUID;

public class FamilyAssert extends AbstractAssert<FamilyAssert, Family> {
    private FamilyAssert(Family actual) {
        super(actual, FamilyAssert.class);
    }

    public static FamilyAssert assertThat(Family actual) {
        return new FamilyAssert(actual);
    }

    public FamilyAssert hasParentsCount(int count) {
        isNotNull();
        if (actual.getParents().size() != count) {
            failWithMessage("Expected parents count to be <%d> but was <%d>", count, actual.getParents().size());
        }
        return this;
    }

    public FamilyAssert hasChildrenCount(int count) {
        isNotNull();
        if (actual.getChildren().size() != count) {
            failWithMessage("Expected children count to be <%d> but was <%d>", count, actual.getChildren().size());
        }
        return this;
    }

    public FamilyAssert hasParent(UUID partyId) {
        isNotNull();
        boolean found = actual.getParents().stream()
                .anyMatch(p -> p.getPartyId().equals(partyId));
        if (!found) {
            failWithMessage("Expected parent with partyId <%s> not found", partyId);
        }
        return this;
    }

    public FamilyAssert hasChild(UUID partyId) {
        isNotNull();
        boolean found = actual.getChildren().stream()
                .anyMatch(c -> c.getPartyId().equals(partyId));
        if (!found) {
            failWithMessage("Expected child with partyId <%s> not found", partyId);
        }
        return this;
    }
}
