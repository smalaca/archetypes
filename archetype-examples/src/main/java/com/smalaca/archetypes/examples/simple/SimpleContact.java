package com.smalaca.archetypes.examples.simple;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.Person
public class SimpleContact {
    private final Person person;
    private final String phoneNumber;

    public SimpleContact(String firstName, String lastName, String phoneNumber) {
        this.person = new Person(firstName, lastName);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Person getPerson() {
        return person;
    }
}
