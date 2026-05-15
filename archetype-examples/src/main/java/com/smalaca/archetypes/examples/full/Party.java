package com.smalaca.archetypes.examples.full;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Party {
    private final List<Address> addresses = new ArrayList<>();
    private final List<ClientAuthentication> authentications = new ArrayList<>();

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public Collection<Address> getAddresses() {
        return List.copyOf(addresses);
    }

    public void addAuthentication(ClientAuthentication authentication) {
        authentications.add(authentication);
    }

    public Collection<ClientAuthentication> getAuthentications() {
        return List.copyOf(authentications);
    }
}
