package com.smalaca.trainingcenter.accounting.domain.buyer;

import com.smalaca.trainingcenter.accounting.domain.taxnumber.TaxNumber;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class BuyerFactoryTest {
    private static final BuyerId DUMMY_BUYER_ID = new BuyerId(UUID.randomUUID());
    private static final TaxNumber DUMMY_TAX_NUMBER = new TaxNumber("PL1234567890");

    private final AccountService accountService = mock(AccountService.class);
    private final LocationService locationService = mock(LocationService.class);
    private final BuyerFactory factory = BuyerFactory.buyerFactory(accountService, locationService);

    @Test
    void shouldCreateBuyerWhenAllConstraintsAreSatisfied() {
        given(accountService.isActive(DUMMY_BUYER_ID)).willReturn(true);
        given(locationService.isEligible(DUMMY_BUYER_ID)).willReturn(true);

        Buyer buyer = factory.create(DUMMY_BUYER_ID, DUMMY_TAX_NUMBER);

        assertThat(buyer).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenActiveAccountConstraintIsNotSatisfied() {
        given(accountService.isActive(DUMMY_BUYER_ID)).willReturn(false);

        assertThatThrownBy(() -> factory.create(DUMMY_BUYER_ID, DUMMY_TAX_NUMBER))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Buyer constraints not satisfied");
    }

    @Test
    void shouldThrowExceptionWhenRegionalEligibilityConstraintIsNotSatisfied() {
        given(accountService.isActive(DUMMY_BUYER_ID)).willReturn(true);
        given(locationService.isEligible(DUMMY_BUYER_ID)).willReturn(false);

        assertThatThrownBy(() -> factory.create(DUMMY_BUYER_ID, DUMMY_TAX_NUMBER))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Buyer constraints not satisfied");
    }
}
