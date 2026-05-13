package com.smalaca.archetypes.examples.organization;

import com.smalaca.archetypes.party.OrganizationUnit;
import org.assertj.core.api.AbstractAssert;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class CorporateEntityAssert extends AbstractAssert<CorporateEntityAssert, CorporateEntity> {
    private CorporateEntityAssert(CorporateEntity actual) {
        super(actual, CorporateEntityAssert.class);
    }

    public static CorporateEntityAssert assertThat(CorporateEntity actual) {
        return new CorporateEntityAssert(actual);
    }

    public CorporateEntityAssert hasName(String name) {
        isNotNull();

        if (!actual.getOrganization().getName().equals(name)) {
            failWithMessage("Expected corporate entity name to be <%s> but was <%s>", name, actual.getOrganization().getName());
        }

        return this;
    }

    public CorporateEntityAssert hasTaxIdentifier(String taxIdentifier) {
        isNotNull();

        if (!actual.getTaxIdentifier().equals(taxIdentifier)) {
            failWithMessage("Expected tax identifier to be <%s> but was <%s>", taxIdentifier, actual.getTaxIdentifier());
        }

        return this;
    }

    public CorporateEntityAssert hasDepartments(String... departmentNames) {
        isNotNull();

        Collection<OrganizationUnit> units = actual.getOrganization().getUnits();
        org.assertj.core.api.Assertions.assertThat(units)
                .extracting(OrganizationUnit::getName)
                .containsExactlyInAnyOrder(departmentNames);

        return this;
    }
}
