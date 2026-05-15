package com.smalaca.archetypes.examples.full;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ClientContactAssert extends AbstractAssert<ClientContactAssert, ClientContact> {
    private ClientContactAssert(ClientContact actual) {
        super(actual, ClientContactAssert.class);
    }

    public static ClientContactAssert assertThat(ClientContact actual) {
        return new ClientContactAssert(actual);
    }

    public ClientContactAssert hasName(String firstName, String lastName) {
        isNotNull();

        if (!actual.getName().firstName().equals(firstName)) {
            failWithMessage("Expected first name to be <%s> but was <%s>", firstName, actual.getName().firstName());
        }

        if (!actual.getName().lastName().equals(lastName)) {
            failWithMessage("Expected last name to be <%s> but was <%s>", lastName, actual.getName().lastName());
        }

        return this;
    }

    public ClientContactAssert hasPosition(String position) {
        isNotNull();

        if (!actual.getPosition().equals(position)) {
            failWithMessage("Expected position to be <%s> but was <%s>", position, actual.getPosition());
        }

        return this;
    }

    public ClientContactAssert hasLoginToken(String token) {
        isNotNull();

        if (actual.getAuthentication() == null) {
            failWithMessage("Expected authentication to be present but was not");
        }

        if (!actual.getAuthentication().value().equals(token)) {
            failWithMessage("Expected authentication token to be <%s> but was <%s>", token, actual.getAuthentication().value());
        }

        return this;
    }

    public ClientContactAssert hasAddressIn(String city) {
        isNotNull();

        Assertions.assertThat(actual.getAddresses())
                .extracting(Address::city)
                .contains(city);

        return this;
    }
}
