package com.smalaca.archetypes.partyrelationship;

public interface RelationshipConstraint {
    boolean isSatisfiedBy(PartyRelationship relationship);
}
