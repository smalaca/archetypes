package com.smalaca.archetypes.examples.simple;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.Person
public class SimpleContact {
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;

    public SimpleContact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
