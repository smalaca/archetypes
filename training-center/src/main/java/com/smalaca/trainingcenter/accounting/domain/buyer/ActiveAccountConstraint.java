package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Specification
@ArchetypeParty.PartyRoleConstraint
class ActiveAccountConstraint implements BuyerConstraint {
    private final AccountService accountService;

    ActiveAccountConstraint(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean isSatisfiedBy(BuyerId buyerId) {
        return accountService.isActive(buyerId);
    }
}
