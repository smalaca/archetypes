package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.party.PartyAuthentication;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ClientContactAssert extends AbstractAssert<ClientContactAssert, ClientContact> {
    private ClientContactAssert(ClientContact actual) {
        super(actual, ClientContactAssert.class);
    }

    public static ClientContactAssert assertThat(ClientContact actual) {
        return new ClientContactAssert(actual);
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

        Assertions.assertThat(actual.getPerson().getAuthentications())
                .extracting(PartyAuthentication::value)
                .containsExactly(token);

        return this;
    }
}
