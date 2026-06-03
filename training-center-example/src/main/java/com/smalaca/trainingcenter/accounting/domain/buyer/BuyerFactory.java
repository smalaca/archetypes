package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.trainingcenter.accounting.domain.taxnumber.TaxNumber;
import java.util.List;

public class BuyerFactory {
    private final List<BuyerConstraint> constraints;

    private BuyerFactory(List<BuyerConstraint> constraints) {
        this.constraints = constraints;
    }

    public static BuyerFactory buyerFactory(AccountService accountService, LocationService locationService) {
        return new BuyerFactory(List.of(
                new ActiveAccountConstraint(accountService),
                new RegionalEligibilityConstraint(locationService)
        ));
    }

    public Buyer create(BuyerId buyerId, TaxNumber taxNumber) {
        if (isSatisfied(buyerId)) {
            return new Buyer(buyerId, taxNumber);
        }

        throw new RuntimeException("Buyer constraints not satisfied");
    }

    private boolean isSatisfied(BuyerId buyerId) {
        return constraints.stream().allMatch(constraint -> constraint.isSatisfiedBy(buyerId));
    }
}
