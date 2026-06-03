package com.smalaca.trainingcenter.accounting.domain.seller;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleConstrain
interface SellerConstraint {
    boolean isSatisfiedBy(SellerId sellerId);
}
