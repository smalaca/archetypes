package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.Archetype;
import com.smalaca.archetypes.annotations.ArchetypePart;
import com.smalaca.archetypes.party.Person;

@Archetype(name = "Party")
@ArchetypePart(archetype = "Party", part = "Person")
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
