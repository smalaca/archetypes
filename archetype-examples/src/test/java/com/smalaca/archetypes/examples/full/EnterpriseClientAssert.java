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

        if (!actual.getOrganization().getName().equals(name)) {
            failWithMessage("Expected client name to be <%s> but was <%s>", name, actual.getOrganization().getName());
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

    public EnterpriseClientAssert hasTaxId(String taxId) {
        isNotNull();

        Assertions.assertThat(actual.getOrganization().getIdentifiers())
                .extracting(PartyIdentifier::identifier)
                .containsExactly(taxId);

        return this;
    }

    public EnterpriseClientAssert hasBranchIn(String city) {
        isNotNull();

        Assertions.assertThat(actual.getOrganization().getAddresses())
                .extracting(Address::city)
                .containsExactly(city);

        return this;
    }
}
