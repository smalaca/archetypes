package com.smalaca.archetypes.examples.full;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Party {
    private final List<Address> addresses = new ArrayList<>();

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public Collection<Address> getAddresses() {
        return List.copyOf(addresses);
    }
}
