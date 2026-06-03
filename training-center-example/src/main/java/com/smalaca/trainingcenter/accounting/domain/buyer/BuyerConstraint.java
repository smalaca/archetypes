package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleConstraint
interface BuyerConstraint {
    boolean isSatisfiedBy(BuyerId buyerId);
}
