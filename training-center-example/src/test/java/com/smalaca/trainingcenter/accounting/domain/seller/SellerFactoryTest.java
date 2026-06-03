package com.smalaca.trainingcenter.accounting.domain.seller;

import com.smalaca.trainingcenter.accounting.domain.taxnumber.TaxNumber;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SellerFactoryTest {
    private static final TaxNumber DUMMY_TAX_NUMBER = new TaxNumber("PL1234567890");
    private static final SellerId DUMMY_SELLER_ID = new SellerId(UUID.randomUUID());

    private final CompanyService companyService = mock(CompanyService.class);
    private final AccreditationService accreditationService = mock(AccreditationService.class);
    private final SellerFactory factory = SellerFactory.sellerFactory(companyService, accreditationService);

    @Test
    void shouldCreateSellerWhenAllConstraintsAreSatisfied() {
        given(companyService.isLegalEntity(DUMMY_SELLER_ID)).willReturn(true);
        given(companyService.hasValidTaxNumber(DUMMY_SELLER_ID)).willReturn(true);
        given(accreditationService.hasValidAccreditation(DUMMY_SELLER_ID)).willReturn(true);

        Seller actual = factory.create(DUMMY_SELLER_ID, DUMMY_TAX_NUMBER);

        assertThat(actual)
                .extracting("sellerId", "taxNumber")
                .containsExactly(DUMMY_SELLER_ID, DUMMY_TAX_NUMBER);
    }

    @Test
    void shouldThrowExceptionWhenLegalEntityConstraintIsNotSatisfied() {
        given(companyService.isLegalEntity(DUMMY_SELLER_ID)).willReturn(false);

        assertThatThrownBy(() -> factory.create(DUMMY_SELLER_ID, DUMMY_TAX_NUMBER))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Seller constraints not satisfied");
    }

    @Test
    void shouldThrowExceptionWhenValidTaxNumberConstraintIsNotSatisfied() {
        given(companyService.isLegalEntity(DUMMY_SELLER_ID)).willReturn(true);
        given(companyService.hasValidTaxNumber(DUMMY_SELLER_ID)).willReturn(false);

        assertThatThrownBy(() -> factory.create(DUMMY_SELLER_ID, DUMMY_TAX_NUMBER))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Seller constraints not satisfied");
    }

    @Test
    void shouldThrowExceptionWhenAccreditationConstraintIsNotSatisfied() {
        given(companyService.isLegalEntity(DUMMY_SELLER_ID)).willReturn(true);
        given(companyService.hasValidTaxNumber(DUMMY_SELLER_ID)).willReturn(true);
        given(accreditationService.hasValidAccreditation(DUMMY_SELLER_ID)).willReturn(false);

        assertThatThrownBy(() -> factory.create(DUMMY_SELLER_ID, DUMMY_TAX_NUMBER))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Seller constraints not satisfied");
    }
}
