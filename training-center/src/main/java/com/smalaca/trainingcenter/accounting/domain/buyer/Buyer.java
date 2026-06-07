package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.accounting.domain.taxnumber.TaxNumber;

@DomainDrivenDesign.AggregateRoot
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
