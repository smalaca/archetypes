package com.smalaca.archetypes.partyrelationship.simple;

import org.assertj.core.api.AbstractAssert;

public class FamilyAssert extends AbstractAssert<FamilyAssert, Family> {
    private FamilyAssert(Family actual) {
        super(actual, FamilyAssert.class);
    }

    public static FamilyAssert assertThat(Family actual) {
        return new FamilyAssert(actual);
    }

    public FamilyAssert hasRelationshipType(String type) {
        isNotNull();
        if (!actual.getRelationshipType().equals(type)) {
            failWithMessage("Expected relationship type to be <%s> but was <%s>", type, actual.getRelationshipType());
        }
        return this;
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

    public FamilyAssert hasParent(String partyId, String person) {
        isNotNull();
        boolean found = actual.getParents().stream()
                .anyMatch(p -> p.getPartyId().equals(partyId) && p.getPerson().equals(person));
        if (!found) {
            failWithMessage("Expected parent with partyId <%s> and person <%s> not found", partyId, person);
        }
        return this;
    }

    public FamilyAssert hasChild(String partyId, String person) {
        isNotNull();
        boolean found = actual.getChildren().stream()
                .anyMatch(c -> c.getPartyId().equals(partyId) && c.getPerson().equals(person));
        if (!found) {
            failWithMessage("Expected child with partyId <%s> and person <%s> not found", partyId, person);
        }
        return this;
    }
}
