package com.smalaca.trainingcenter.accounting.domain.seller;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleConstrain
class AccreditationConstraint implements SellerConstraint {
    @Override
    public boolean isSatisfiedBy(SellerId sellerId) {
        return true;
    }
}
