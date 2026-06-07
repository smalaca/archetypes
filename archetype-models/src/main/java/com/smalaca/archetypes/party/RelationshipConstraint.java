package com.smalaca.archetypes.party;

public interface RelationshipConstraint {
    boolean isSatisfiedBy(PartyRelationship relationship);
}
