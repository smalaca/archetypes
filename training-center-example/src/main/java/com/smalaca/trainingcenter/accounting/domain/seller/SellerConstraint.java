package com.smalaca.trainingcenter.accounting.domain.seller;

import com.smalaca.annotations.archetypes.ArchetypeParty;

@ArchetypeParty.PartyRoleConstraint
interface SellerConstraint {
    boolean isSatisfiedBy(SellerId sellerId);
}
