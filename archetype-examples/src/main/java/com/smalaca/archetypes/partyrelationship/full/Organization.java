package com.smalaca.archetypes.partyrelationship.full;

public class Organization extends Party {
    private final String name;

    public Organization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
