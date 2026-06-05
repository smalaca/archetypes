package com.smalaca.trainingcenter.accounting.domain.seller;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;
import com.smalaca.trainingcenter.accounting.domain.taxnumber.TaxNumber;

@DomainDrivenDesign.AggregateRoot
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
