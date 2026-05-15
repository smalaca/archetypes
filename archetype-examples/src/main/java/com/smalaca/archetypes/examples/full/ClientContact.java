package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.Person
public class ClientContact extends Party {
    @ArchetypeParty.PartyIdentifier
    private final ClientContactName name;
    private final String position;
    @ArchetypeParty.PartyAuthentication
    private ClientAuthentication authentication;

    public ClientContact(ClientContactName name, String position) {
        this.name = name;
        this.position = position;
    }

    public ClientContactName getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public void setAuthentication(ClientAuthentication authentication) {
        this.authentication = authentication;
    }

    public ClientAuthentication getAuthentication() {
        return authentication;
    }
}
