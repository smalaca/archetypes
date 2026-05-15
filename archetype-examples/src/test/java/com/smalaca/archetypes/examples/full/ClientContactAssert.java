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

        Assertions.assertThat(actual.getAuthentications())
                .extracting(ClientAuthentication::value)
                .containsExactly(token);

        return this;
    }
}
