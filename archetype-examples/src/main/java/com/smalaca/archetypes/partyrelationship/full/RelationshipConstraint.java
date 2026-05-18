package com.smalaca.archetypes.partyrelationship.full;

public interface RelationshipConstraint {
    boolean isSatisfiedBy(PartyRelationship relationship);
}
