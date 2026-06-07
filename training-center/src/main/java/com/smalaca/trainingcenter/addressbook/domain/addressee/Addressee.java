package com.smalaca.trainingcenter.addressbook.domain.addressee;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.ArrayList;
import java.util.List;

@DomainDrivenDesign.AggregateRoot
@ArchetypeParty.Party
public class Addressee {
    private final AddresseeId addresseeId;
    private final List<Address> address = new ArrayList<>();

    Addressee(AddresseeId addresseeId) {
        this.addresseeId = addresseeId;
    }
}
