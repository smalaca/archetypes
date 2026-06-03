package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleConstrain
class RegionalEligibilityConstraint implements BuyerConstraint {
    @Override
    public boolean isSatisfiedBy(BuyerId buyerId) {
        return true;
    }
}
