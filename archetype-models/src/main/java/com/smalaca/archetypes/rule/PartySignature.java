package com.smalaca.archetypes.rule;

import com.smalaca.archetypes.party.Party;

public class PartySignature {
    private final Party party;

    public PartySignature(Party party) {
        this.party = party;
    }

    public Party getParty() {
        return party;
    }
}
