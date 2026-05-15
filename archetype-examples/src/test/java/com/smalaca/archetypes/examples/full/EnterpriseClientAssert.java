package com.smalaca.archetypes.examples.full;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class EnterpriseClientAssert extends AbstractAssert<EnterpriseClientAssert, EnterpriseClient> {
    private EnterpriseClientAssert(EnterpriseClient actual) {
        super(actual, EnterpriseClientAssert.class);
    }

    public static EnterpriseClientAssert assertThat(EnterpriseClient actual) {
        return new EnterpriseClientAssert(actual);
    }

    public EnterpriseClientAssert hasName(String name) {
        isNotNull();

        if (!actual.getName().equals(name)) {
            failWithMessage("Expected client name to be <%s> but was <%s>", name, actual.getName());
        }

        return this;
    }

    public EnterpriseClientAssert hasIndustry(String industry) {
        isNotNull();

        if (!actual.getIndustry().equals(industry)) {
            failWithMessage("Expected industry to be <%s> but was <%s>", industry, actual.getIndustry());
        }

        return this;
    }

    public EnterpriseClientAssert hasVatNumber(String vatNumber) {
        isNotNull();

        if (!actual.getVatNumber().value().equals(vatNumber)) {
            failWithMessage("Expected VAT number to be <%s> but was <%s>", vatNumber, actual.getVatNumber().value());
        }

        return this;
    }

    public EnterpriseClientAssert hasBranchIn(String city) {
        isNotNull();

        Assertions.assertThat(actual.getAddresses())
                .extracting(Address::city)
                .containsExactly(city);

        return this;
    }

    public EnterpriseClientAssert hasBranch(String name, String code) {
        isNotNull();

        ClientBranch branch = actual.getUnits().stream()
                .filter(unit -> unit.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (branch == null) {
            failWithMessage("Expected client to have branch <%s> but was not found", name);
        }

        if (!branch.getBranchCode().value().equals(code)) {
            failWithMessage("Expected branch <%s> to have code <%s> but was <%s>", name, code, branch.getBranchCode().value());
        }

        return this;
    }
}
