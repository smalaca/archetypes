package com.smalaca.archetypes.examples.simple;

import com.smalaca.archetypes.annotations.Archetype;
import com.smalaca.archetypes.annotations.ArchetypePart;
import com.smalaca.archetypes.party.Person;

@Archetype(name = "Party")
@ArchetypePart(archetype = "Party", part = "Person")
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
