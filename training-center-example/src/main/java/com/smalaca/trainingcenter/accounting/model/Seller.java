package com.smalaca.trainingcenter.accounting.model;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRole
public class Seller {
    private final BuyerId buyerId;
    private final TaxNumber taxNumber;

    public Seller(BuyerId buyerId, TaxNumber taxNumber) {
        this.buyerId = buyerId;
        this.taxNumber = taxNumber;
    }
}
