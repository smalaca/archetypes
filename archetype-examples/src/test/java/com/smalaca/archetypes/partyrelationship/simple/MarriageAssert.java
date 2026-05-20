package com.smalaca.archetypes.partyrelationship.simple;

import org.assertj.core.api.AbstractAssert;

public class MarriageAssert extends AbstractAssert<MarriageAssert, Marriage> {
    private MarriageAssert(Marriage actual) {
        super(actual, MarriageAssert.class);
    }

    public static MarriageAssert assertThat(Marriage actual) {
        return new MarriageAssert(actual);
    }

    public MarriageAssert hasRelationshipType(String type) {
        isNotNull();
        if (!actual.getRelationshipType().equals(type)) {
            failWithMessage("Expected relationship type to be <%s> but was <%s>", type, actual.getRelationshipType());
        }
        return this;
    }

    public MarriageAssert hasSpouse1(String partyId, String person) {
        isNotNull();
        if (!actual.getSpouse1().getPartyId().equals(partyId)) {
            failWithMessage("Expected spouse1 partyId to be <%s> but was <%s>", partyId, actual.getSpouse1().getPartyId());
        }
        if (!actual.getSpouse1().getPerson().equals(person)) {
            failWithMessage("Expected spouse1 person to be <%s> but was <%s>", person, actual.getSpouse1().getPerson());
        }
        return this;
    }

    public MarriageAssert hasSpouse2(String partyId, String person) {
        isNotNull();
        if (!actual.getSpouse2().getPartyId().equals(partyId)) {
            failWithMessage("Expected spouse2 partyId to be <%s> but was <%s>", partyId, actual.getSpouse2().getPartyId());
        }
        if (!actual.getSpouse2().getPerson().equals(person)) {
            failWithMessage("Expected spouse2 person to be <%s> but was <%s>", person, actual.getSpouse2().getPerson());
        }
        return this;
    }

    public MarriageAssert hasSpouseRoleType(String roleType) {
        isNotNull();
        if (!actual.getSpouse1().getRoleType().equals(roleType)) {
            failWithMessage("Expected spouse role type to be <%s> but was <%s>", roleType, actual.getSpouse1().getRoleType());
        }
        return this;
    }
}
