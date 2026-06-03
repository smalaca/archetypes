package com.smalaca.trainingcenter.accounting.domain.seller;

import com.smalaca.trainingcenter.accounting.domain.taxnumber.TaxNumber;
import java.util.List;

public class SellerFactory {
    private final List<SellerConstraint> constraints;

    private SellerFactory(List<SellerConstraint> constraints) {
        this.constraints = constraints;
    }

    public static SellerFactory sellerFactory(CompanyService companyService, AccreditationService accreditationService) {
        return new SellerFactory(List.of(
                new LegalEntityConstraint(companyService),
                new ValidTaxNumberConstraint(companyService),
                new AccreditationConstraint(accreditationService)
        ));
    }

    public Seller create(SellerId sellerId, TaxNumber taxNumber) {
        if (isSatisfied(sellerId)) {
            return new Seller(sellerId, taxNumber);
        }

        throw new RuntimeException("Seller constraints not satisfied");
    }

    private boolean isSatisfied(SellerId sellerId) {
        return constraints.stream().allMatch(constraint -> constraint.isSatisfiedBy(sellerId));
    }
}
