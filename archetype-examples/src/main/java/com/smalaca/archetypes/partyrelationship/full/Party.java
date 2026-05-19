package com.smalaca.archetypes.partyrelationship.full;

import com.smalaca.archetypes.annotations.ArchetypePartyRelationship;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ArchetypePartyRelationship.Party
public abstract class Party {
    private final List<PartyIdentifier> identifiers = new ArrayList<>();

    public void addIdentifier(PartyIdentifier identifier) {
        identifiers.add(identifier);
    }

    public Collection<PartyIdentifier> getIdentifiers() {
        return List.copyOf(identifiers);
    }
}
