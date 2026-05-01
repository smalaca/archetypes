package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.archetypes.annotations.ArchetypePartyPerson;
import com.smalaca.archetypes.examples.party.Person;

@ArchetypeParty
@ArchetypePartyPerson
public class ClientContact extends Person {
    private final String position;

    public ClientContact(String firstName, String lastName, String position) {
        super(firstName, lastName);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
