package com.smalaca.archetypes.examples.simple;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.archetypes.annotations.ArchetypePartyPerson;
import com.smalaca.archetypes.examples.party.Person;

@ArchetypeParty
@ArchetypePartyPerson
public class SimpleContact extends Person {
    private final String phoneNumber;

    public SimpleContact(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
