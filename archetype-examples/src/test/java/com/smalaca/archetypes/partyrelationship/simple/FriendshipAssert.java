package com.smalaca.archetypes.partyrelationship.simple;

import org.assertj.core.api.AbstractAssert;

public class FriendshipAssert extends AbstractAssert<FriendshipAssert, Friendship> {
    private FriendshipAssert(Friendship actual) {
        super(actual, FriendshipAssert.class);
    }

    public static FriendshipAssert assertThat(Friendship actual) {
        return new FriendshipAssert(actual);
    }

    public FriendshipAssert isBetween(String person1, String person2) {
        isNotNull();

        if (!actual.getPerson1().equals(person1)) {
            failWithMessage("Expected person1 to be <%s> but was <%s>", person1, actual.getPerson1());
        }

        if (!actual.getPerson2().equals(person2)) {
            failWithMessage("Expected person2 to be <%s> but was <%s>", person2, actual.getPerson2());
        }

        return this;
    }
}
