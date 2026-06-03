package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.trainingcenter.accounting.domain.taxnumber.TaxNumber;
import java.util.List;

public class BuyerFactory {
    private final List<BuyerConstraint> constraints;

    BuyerFactory(List<BuyerConstraint> constraints) {
        this.constraints = constraints;
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
