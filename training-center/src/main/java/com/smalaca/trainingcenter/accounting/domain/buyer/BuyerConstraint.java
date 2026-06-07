package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Specification
@ArchetypeParty.PartyRoleConstraint
interface BuyerConstraint {
    boolean isSatisfiedBy(BuyerId buyerId);
}
