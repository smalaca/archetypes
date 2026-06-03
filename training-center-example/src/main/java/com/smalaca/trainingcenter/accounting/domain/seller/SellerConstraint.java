package com.smalaca.trainingcenter.accounting.domain.seller;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleConstraint
interface SellerConstraint {
    boolean isSatisfiedBy(SellerId sellerId);
}
