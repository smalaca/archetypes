package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleConstraint
class ActiveAccountConstraint implements BuyerConstraint {
    @Override
    public boolean isSatisfiedBy(BuyerId buyerId) {
        return true;
    }
}
