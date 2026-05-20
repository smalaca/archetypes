package com.smalaca.archetypes.partyrelationship.simple;

import org.assertj.core.api.AbstractAssert;

import java.util.UUID;

public class FriendshipAssert extends AbstractAssert<FriendshipAssert, Friendship> {
    private FriendshipAssert(Friendship actual) {
        super(actual, FriendshipAssert.class);
    }

    public static FriendshipAssert assertThat(Friendship actual) {
        return new FriendshipAssert(actual);
    }

    public FriendshipAssert hasFriend1(UUID partyId) {
        isNotNull();
        if (!actual.getFriend1().getPartyId().equals(partyId)) {
            failWithMessage("Expected friend1 partyId to be <%s> but was <%s>", partyId, actual.getFriend1().getPartyId());
        }
        return this;
    }

    public FriendshipAssert hasFriend2(UUID partyId) {
        isNotNull();
        if (!actual.getFriend2().getPartyId().equals(partyId)) {
            failWithMessage("Expected friend2 partyId to be <%s> but was <%s>", partyId, actual.getFriend2().getPartyId());
        }
        return this;
    }
}
