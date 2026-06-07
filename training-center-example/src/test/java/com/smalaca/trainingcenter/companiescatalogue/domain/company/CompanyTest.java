package com.smalaca.trainingcenter.companiescatalogue.domain.company;

import com.smalaca.trainingcenter.companiescatalogue.domain.representation.Representation;
import com.smalaca.trainingcenter.companiescatalogue.domain.representation.RepresentationFactory;
import com.smalaca.trainingcenter.companiescatalogue.domain.representation.RepresentationRepository;
import com.smalaca.trainingcenter.companiescatalogue.domain.representative.RepresentativeId;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CompanyTest {
    private static final CompanyId COMPANY_ID = new CompanyId(UUID.randomUUID());
    private static final List<CompanyRegisteredIdentifier> REGISTERED_IDENTIFIERS = List.of(
            new TaxIdentificationNumber(UUID.randomUUID().toString(), UUID.randomUUID().toString()),
            new NationalBusinessRegister(UUID.randomUUID().toString())
    );
    private static final CompanyName PRIMARY_NAME = new CompanyName("Smalaca Corporation");
    private static final BusinessUnitId BUSINESS_UNIT_ID = new BusinessUnitId(UUID.randomUUID());
    private static final RepresentativeId REPRESENTATIVE_ID = new RepresentativeId(UUID.randomUUID());

    private final RepresentationRepository repository = mock(RepresentationRepository.class);
    private final RepresentationFactory representationFactory = RepresentationFactory.representationFactory(repository);

    @Test
    void shouldCreateCompanyWithPrimaryName() {
        Company company = givenCompany();

        assertThat(company).extracting("companyId").isEqualTo(COMPANY_ID);
        assertThat(company).extracting("registeredIdentifiers").isEqualTo(REGISTERED_IDENTIFIERS);
        assertThat(company).extracting("primaryName").isEqualTo(PRIMARY_NAME);
    }

    @Test
    void shouldAddAlternativeNames() {
        Company company = givenCompany();
        CompanyName alternativeName1 = new CompanyName("Smalaca Corp");
        CompanyName alternativeName2 = new CompanyName("Smalaca Inc");

        company.add(alternativeName1);
        company.add(alternativeName2);

        assertThat(company).extracting("alternativeNames").asList().containsExactly(alternativeName1, alternativeName2);
    }

    @Test
    void shouldAddBusinessUnits() {
        Company company = givenCompany();
        BusinessUnit businessUnit1 = new BusinessUnit(BUSINESS_UNIT_ID, "Training");
        BusinessUnit businessUnit2 = new BusinessUnit(BUSINESS_UNIT_ID, "Consulting");

        company.add(businessUnit1);
        company.add(businessUnit2);

        assertThat(company).extracting("businessUnits").asList().containsExactly(businessUnit1, businessUnit2);
    }
    @Test
    void shouldCreateRepresentationWhenAllConstraintsAreSatisfied() {
        given(repository.isRepresentingAnyOtherCompany(REPRESENTATIVE_ID, COMPANY_ID)).willReturn(false);
        given(repository.countBusinessUnitsRepresentedIn(REPRESENTATIVE_ID, COMPANY_ID)).willReturn(0);
        given(repository.isBusinessUnitAlreadyRepresented(BUSINESS_UNIT_ID)).willReturn(false);

        Optional<Representation> actual = givenCompanyWithBusinessUnit().represent(REPRESENTATIVE_ID, BUSINESS_UNIT_ID, representationFactory);

        assertThat(actual).isPresent();
        Representation representation = actual.get();
        assertThat(representation).extracting("representativeId").isEqualTo(REPRESENTATIVE_ID);
        assertThat(representation).extracting("businessUnitId").isEqualTo(BUSINESS_UNIT_ID);
        assertThat(representation).extracting("representationId").isNotNull();
    }

    @Test
    void shouldNotCreateRepresentationWhenAlreadyRepresentingAnotherCompany() {
        given(repository.isRepresentingAnyOtherCompany(REPRESENTATIVE_ID, COMPANY_ID)).willReturn(true);
        given(repository.countBusinessUnitsRepresentedIn(REPRESENTATIVE_ID, COMPANY_ID)).willReturn(0);

        Optional<Representation> actual = givenCompanyWithBusinessUnit().represent(REPRESENTATIVE_ID, BUSINESS_UNIT_ID, representationFactory);

        assertThat(actual).isEmpty();
    }

    @Test
    void shouldNotCreateRepresentationWhenMaxBusinessUnitsInCompanyReached() {
        given(repository.isRepresentingAnyOtherCompany(REPRESENTATIVE_ID, COMPANY_ID)).willReturn(false);
        given(repository.countBusinessUnitsRepresentedIn(REPRESENTATIVE_ID, COMPANY_ID)).willReturn(3);

        Optional<Representation> actual = givenCompanyWithBusinessUnit().represent(REPRESENTATIVE_ID, BUSINESS_UNIT_ID, representationFactory);

        assertThat(actual).isEmpty();
    }

    @Test
    void shouldNotCreateRepresentationWhenBusinessUnitAlreadyRepresented() {
        given(repository.isRepresentingAnyOtherCompany(REPRESENTATIVE_ID, COMPANY_ID)).willReturn(false);
        given(repository.countBusinessUnitsRepresentedIn(REPRESENTATIVE_ID, COMPANY_ID)).willReturn(0);
        given(repository.isBusinessUnitAlreadyRepresented(BUSINESS_UNIT_ID)).willReturn(true);

        Optional<Representation> actual = givenCompanyWithBusinessUnit().represent(REPRESENTATIVE_ID, BUSINESS_UNIT_ID, representationFactory);

        assertThat(actual).isEmpty();
    }

    @Test
    void shouldNotCreateRepresentationWhenBusinessUnitDoesNotExist() {
        given(repository.isRepresentingAnyOtherCompany(REPRESENTATIVE_ID, COMPANY_ID)).willReturn(false);
        given(repository.countBusinessUnitsRepresentedIn(REPRESENTATIVE_ID, COMPANY_ID)).willReturn(0);
        BusinessUnitId otherBusinessUnitId = new BusinessUnitId(UUID.randomUUID());

        Optional<Representation> actual = givenCompanyWithBusinessUnit().represent(REPRESENTATIVE_ID, otherBusinessUnitId, representationFactory);

        assertThat(actual).isEmpty();
    }

    public Company givenCompanyWithBusinessUnit() {
        Company company = givenCompany();
        company.add(new BusinessUnit(BUSINESS_UNIT_ID, UUID.randomUUID().toString()));
        return company;
    }

    private Company givenCompany() {
        return new Company(COMPANY_ID, REGISTERED_IDENTIFIERS, PRIMARY_NAME);
    }
}
