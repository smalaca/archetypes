package com.smalaca.trainingcenter.accounting.domain.seller;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.trainingcenter.accounting.domain.taxnumber.TaxNumber;

@ArchetypeParty.PartyRole
@ArchetypeParty.PartyRoleType
public class Seller {
    private final SellerId sellerId;
    private final TaxNumber taxNumber;

    Seller(SellerId sellerId, TaxNumber taxNumber) {
        this.sellerId = sellerId;
        this.taxNumber = taxNumber;
    }
}
