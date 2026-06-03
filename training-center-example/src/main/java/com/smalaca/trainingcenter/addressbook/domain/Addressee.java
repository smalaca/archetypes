package com.smalaca.trainingcenter.addressbook.domain;

import com.smalaca.archetypes.annotations.ArchetypeParty;

import java.util.ArrayList;
import java.util.List;

@ArchetypeParty.Party
public class Addressee {
    private final AddresseeId addresseeId;
    private final List<Address> address = new ArrayList<>();

    public Addressee(AddresseeId addresseeId) {
        this.addresseeId = addresseeId;
    }
}
