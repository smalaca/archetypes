package com.smalaca.archetypes.partyrelationship.simple;

import org.assertj.core.api.AbstractAssert;

import java.util.UUID;

public class MarriageAssert extends AbstractAssert<MarriageAssert, Marriage> {
    private MarriageAssert(Marriage actual) {
        super(actual, MarriageAssert.class);
    }

    public static MarriageAssert assertThat(Marriage actual) {
        return new MarriageAssert(actual);
    }

    public MarriageAssert hasSpouse1(UUID partyId) {
        isNotNull();
        if (!actual.getSpouse1().getPartyId().equals(partyId)) {
            failWithMessage("Expected spouse1 partyId to be <%s> but was <%s>", partyId, actual.getSpouse1().getPartyId());
        }
        return this;
    }

    public MarriageAssert hasSpouse2(UUID partyId) {
        isNotNull();
        if (!actual.getSpouse2().getPartyId().equals(partyId)) {
            failWithMessage("Expected spouse2 partyId to be <%s> but was <%s>", partyId, actual.getSpouse2().getPartyId());
        }
        return this;
    }
}
