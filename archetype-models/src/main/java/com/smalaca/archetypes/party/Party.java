package com.smalaca.archetypes.party;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Party {
    private final List<PartyIdentifier> identifiers = new ArrayList<>();
    private final List<Address> addresses = new ArrayList<>();
    private final List<PartyAuthentication> authentications = new ArrayList<>();

    public void addIdentifier(PartyIdentifier identifier) {
        identifiers.add(identifier);
    }

    public Collection<PartyIdentifier> getIdentifiers() {
        return List.copyOf(identifiers);
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public Collection<Address> getAddresses() {
        return List.copyOf(addresses);
    }

    public void addAuthentication(PartyAuthentication authentication) {
        authentications.add(authentication);
    }

    public Collection<PartyAuthentication> getAuthentications() {
        return List.copyOf(authentications);
    }
}
