package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Specification
@ArchetypeParty.PartyRoleConstraint
class RegionalEligibilityConstraint implements BuyerConstraint {
    private final LocationService locationService;

    RegionalEligibilityConstraint(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public boolean isSatisfiedBy(BuyerId buyerId) {
        return locationService.isEligible(buyerId);
    }
}
