package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.trainingcenter.accounting.domain.taxnumber.TaxNumber;

@ArchetypeParty.PartyRole
@ArchetypeParty.PartyRoleType
public class Buyer {
    private final BuyerId buyerId;
    private final TaxNumber taxNumber;

    Buyer(BuyerId buyerId, TaxNumber taxNumber) {
        this.buyerId = buyerId;
        this.taxNumber = taxNumber;
    }
}
