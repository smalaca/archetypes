package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.Person
public class ClientContact {
    private final Person person;
    @ArchetypeParty.PartyIdentifier
    private final Username username;
    private final String position;

    public ClientContact(Username username, String firstName, String lastName, String position) {
        this.person = new Person(firstName, lastName);
        this.username = username;
        this.position = position;
    }

    public Username getUsername() {
        return username;
    }

    public String getPosition() {
        return position;
    }

    public Person getPerson() {
        return person;
    }
}
