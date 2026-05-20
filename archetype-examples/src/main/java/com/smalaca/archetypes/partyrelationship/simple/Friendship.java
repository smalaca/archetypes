package com.smalaca.archetypes.partyrelationship.simple;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;

import java.util.UUID;

@ArchetypePartyRelationship.PartyRelationship
public class Friendship {
    @ArchetypePartyRelationship.PartyRole
    private final FriendRole friend1;
    @ArchetypePartyRelationship.PartyRole
    private final FriendRole friend2;

    public Friendship(UUID partyId1, UUID partyId2) {
        this.friend1 = new FriendRole(partyId1);
        this.friend2 = new FriendRole(partyId2);
    }

    public FriendRole getFriend1() {
        return friend1;
    }

    public FriendRole getFriend2() {
        return friend2;
    }
}
