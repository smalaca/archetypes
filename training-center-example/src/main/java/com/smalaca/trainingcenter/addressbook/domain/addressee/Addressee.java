package com.smalaca.trainingcenter.addressbook.domain.addressee;

import com.smalaca.annotations.archetypes.ArchetypeParty;

import java.util.ArrayList;
import java.util.List;

@ArchetypeParty.Party
public class Addressee {
    private final AddresseeId addresseeId;
    private final List<Address> address = new ArrayList<>();

    Addressee(AddresseeId addresseeId) {
        this.addresseeId = addresseeId;
    }
}
