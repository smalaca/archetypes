package com.smalaca.archetypes.partyrelationship;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Party {
    private final List<PartyIdentifier> identifiers = new ArrayList<>();

    public void addIdentifier(PartyIdentifier identifier) {
        identifiers.add(identifier);
    }

    public Collection<PartyIdentifier> getIdentifiers() {
        return List.copyOf(identifiers);
    }
}
