package com.smalaca.archetypes.examples.simple;

import org.assertj.core.api.AbstractAssert;

public class SimpleContactAssert extends AbstractAssert<SimpleContactAssert, SimpleContact> {
    private SimpleContactAssert(SimpleContact actual) {
        super(actual, SimpleContactAssert.class);
    }

    public static SimpleContactAssert assertThat(SimpleContact actual) {
        return new SimpleContactAssert(actual);
    }

    public SimpleContactAssert hasUsername(String username) {
        isNotNull();

        if (!actual.getUsername().equals(username)) {
            failWithMessage("Expected username to be <%s> but was <%s>", username, actual.getUsername());
        }

        return this;
    }

    public SimpleContactAssert hasPerson(String firstName, String lastName) {
        isNotNull();

        if (!actual.getFirstName().equals(firstName)) {
            failWithMessage("Expected person's first name to be <%s> but was <%s>", firstName, actual.getFirstName());
        }

        if (!actual.getLastName().equals(lastName)) {
            failWithMessage("Expected person's last name to be <%s> but was <%s>", lastName, actual.getLastName());
        }

        return this;
    }

    public SimpleContactAssert hasPhoneNumber(String phoneNumber) {
        isNotNull();

        if (!actual.getPhoneNumber().equals(phoneNumber)) {
            failWithMessage("Expected phone number to be <%s> but was <%s>", phoneNumber, actual.getPhoneNumber());
        }

        return this;
    }
}
