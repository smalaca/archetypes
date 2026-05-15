package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ArchetypeParty.Organization
public class Organization extends Party {
    private String name;
    private final List<ClientBranch> units = new ArrayList<>();

    public Organization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addUnit(ClientBranch unit) {
        units.add(unit);
    }

    public Collection<ClientBranch> getUnits() {
        return List.copyOf(units);
    }
}
