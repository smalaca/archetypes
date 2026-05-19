package com.smalaca.archetypes.partyrelationship.simple;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

@ArchetypePartyRelationship.PartyRelationship
public class Friendship {
    @ArchetypePartyRelationship.RelationshipType
    private final String relationshipType = "Friendship";
    @ArchetypePartyRelationship.PartyRole
    private final FriendRole friend1;
    @ArchetypePartyRelationship.PartyRole
    private final FriendRole friend2;

    public Friendship(String person1, String person2) {
        this.friend1 = new FriendRole(person1);
        this.friend2 = new FriendRole(person2);
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public FriendRole getFriend1() {
        return friend1;
    }

    public FriendRole getFriend2() {
        return friend2;
    }

    @ArchetypePartyRelationship.PartyRole
    public static class FriendRole {
        @ArchetypePartyRelationship.RoleType
        private final String roleType = "Friend";
        @ArchetypePartyRelationship.Party
        private final String person;

        public FriendRole(String person) {
            this.person = person;
        }

        public String getRoleType() {
            return roleType;
        }

        public String getPerson() {
            return person;
        }
    }
}
