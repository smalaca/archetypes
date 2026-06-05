package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.annotations.archetypes.ArchetypeParty;

@ArchetypeParty.PartyRoleConstraint
interface BuyerConstraint {
    boolean isSatisfiedBy(BuyerId buyerId);
}
