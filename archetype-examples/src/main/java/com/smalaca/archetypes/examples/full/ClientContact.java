package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.archetypes.party.Person;

@ArchetypeParty.Person
public class ClientContact {
    private final Person person;
    private final String position;

    public ClientContact(String firstName, String lastName, String position) {
        this.person = new Person(firstName, lastName);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public Person getPerson() {
        return person;
    }
}
