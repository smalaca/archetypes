package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.archetypes.annotations.ArchetypeParty;

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
