package com.smalaca.trainingcenter.accounting.model;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRole
@ArchetypeParty.PartyRoleType
public class Buyer {
    private final BuyerId buyerId;
    private final TaxNumber taxNumber;

    public Buyer(BuyerId buyerId, TaxNumber taxNumber) {
        this.buyerId = buyerId;
        this.taxNumber = taxNumber;
    }
}
