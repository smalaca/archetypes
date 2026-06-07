package com.smalaca.trainingcenter.companiescatalogue.domain.company;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyTest {
    private static final CompanyId COMPANY_ID = new CompanyId(UUID.randomUUID());
    private static final List<CompanyRegisteredIdentifier> REGISTERED_IDENTIFIERS = List.of(
            new TaxIdentificationNumber(UUID.randomUUID().toString(), UUID.randomUUID().toString()),
            new NationalBusinessRegister(UUID.randomUUID().toString())
    );
    private static final CompanyName PRIMARY_NAME = new CompanyName("Smalaca Corporation");

    @Test
    void shouldCreateCompanyWithPrimaryName() {
        Company company = new Company(COMPANY_ID, REGISTERED_IDENTIFIERS, PRIMARY_NAME);

        assertThat(company).extracting("companyId").isEqualTo(COMPANY_ID);
        assertThat(company).extracting("registeredIdentifiers").isEqualTo(REGISTERED_IDENTIFIERS);
        assertThat(company).extracting("primaryName").isEqualTo(PRIMARY_NAME);
    }

    @Test
    void shouldAddAlternativeNames() {
        Company company = new Company(COMPANY_ID, REGISTERED_IDENTIFIERS, PRIMARY_NAME);
        CompanyName alternativeName1 = new CompanyName("Smalaca Corp");
        CompanyName alternativeName2 = new CompanyName("Smalaca Inc");

        company.add(alternativeName1);
        company.add(alternativeName2);

        assertThat(company).extracting("alternativeNames").asList().containsExactly(alternativeName1, alternativeName2);
    }

    @Test
    void shouldAddBusinessUnits() {
        Company company = new Company(COMPANY_ID, REGISTERED_IDENTIFIERS, PRIMARY_NAME);
        BusinessUnit businessUnit1 = new BusinessUnit(new BusinessUnitId(UUID.randomUUID()), "Training");
        BusinessUnit businessUnit2 = new BusinessUnit(new BusinessUnitId(UUID.randomUUID()), "Consulting");

        company.add(businessUnit1);
        company.add(businessUnit2);

        assertThat(company).extracting("businessUnits").asList().containsExactly(businessUnit1, businessUnit2);
    }
}
