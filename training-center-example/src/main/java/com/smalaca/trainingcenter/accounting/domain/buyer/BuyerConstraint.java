package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleConstrain
interface BuyerConstraint {
    boolean isSatisfiedBy(BuyerId buyerId);
}
