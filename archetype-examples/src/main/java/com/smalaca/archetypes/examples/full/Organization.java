package com.smalaca.archetypes.examples.full;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ArchetypeParty.Organization
public class Organization extends Party {
    private String name;
    private final List<OrganizationUnit> units = new ArrayList<>();

    public Organization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addUnit(OrganizationUnit unit) {
        units.add(unit);
    }

    public Collection<OrganizationUnit> getUnits() {
        return List.copyOf(units);
    }
}
