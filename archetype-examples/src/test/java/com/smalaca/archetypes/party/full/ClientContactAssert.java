package com.smalaca.archetypes.party.full;

import org.assertj.core.api.AbstractAssert;

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

    public ClientContactAssert hasAddressIn(String city, String street) {
        isNotNull();

        Address address = actual.getAddresses().stream()
                .filter(a -> a.city().equals(city) && a.street().equals(street))
                .findFirst()
                .orElse(null);

        if (address == null) {
            failWithMessage("Expected client contact to have address in <%s> at <%s> but was not found", city, street);
        }

        return this;
    }

    public ClientContactAssert hasAddresses(int count) {
        isNotNull();

        if (actual.getAddresses().size() != count) {
            failWithMessage("Expected client contact to have <%s> addresses but had <%s>", count, actual.getAddresses().size());
        }

        return this;
    }

    public ClientContactAssert hasNoAddresses() {
        isNotNull();

        if (!actual.getAddresses().isEmpty()) {
            failWithMessage("Expected client contact to have no addresses but had <%s>", actual.getAddresses().size());
        }

        return this;
    }
}
