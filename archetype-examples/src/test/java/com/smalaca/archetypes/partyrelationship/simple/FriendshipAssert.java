package com.smalaca.archetypes.partyrelationship.simple;

import org.assertj.core.api.AbstractAssert;

public class FriendshipAssert extends AbstractAssert<FriendshipAssert, Friendship> {
    private FriendshipAssert(Friendship actual) {
        super(actual, FriendshipAssert.class);
    }

    public static FriendshipAssert assertThat(Friendship actual) {
        return new FriendshipAssert(actual);
    }

    public FriendshipAssert hasRelationshipType(String type) {
        isNotNull();
        if (!actual.getRelationshipType().equals(type)) {
            failWithMessage("Expected relationship type to be <%s> but was <%s>", type, actual.getRelationshipType());
        }
        return this;
    }

    public FriendshipAssert hasFriend1(String person) {
        isNotNull();
        if (!actual.getFriend1().getPerson().equals(person)) {
            failWithMessage("Expected friend1 to be <%s> but was <%s>", person, actual.getFriend1().getPerson());
        }
        return this;
    }

    public FriendshipAssert hasFriend2(String person) {
        isNotNull();
        if (!actual.getFriend2().getPerson().equals(person)) {
            failWithMessage("Expected friend2 to be <%s> but was <%s>", person, actual.getFriend2().getPerson());
        }
        return this;
    }

    public FriendshipAssert hasFriendRoleType(String roleType) {
        isNotNull();
        if (!actual.getFriend1().getRoleType().equals(roleType)) {
            failWithMessage("Expected friend role type to be <%s> but was <%s>", roleType, actual.getFriend1().getRoleType());
        }
        return this;
    }
}
