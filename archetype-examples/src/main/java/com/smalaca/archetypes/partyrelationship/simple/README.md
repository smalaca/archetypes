# Simple Friendship Example

This example demonstrates a **simplified** usage of the **PartyRelationship Archetype Pattern**.

## Business Context
A basic social network where two people can be friends.

## Archetype Usage
In this example, the **PartyRelationship Archetype Pattern** is applied in its most simplified form:

1. **Friendship** realizes **PartyRelationship**.

It **does not** use separate classes for:
- `PartyRole`
- `RoleType`
- `RelationshipType`
- `RelationshipConstraint`
- `Party` (Persons are represented as simple Strings)

## Justification of Usage
For a simple friendship relationship where roles are symmetric (both are "friends"), a consolidated model is often sufficient.

**Why this is enough:**
- **Extreme Simplicity:** `Friendship` directly captures the relationship between two entities.
- **Goal Achievement:** The primary goal is to represent a link between two people.

## Technical Implementation
- **Self-Contained**: This example is completely independent.
- `@ArchetypePartyRelationship.PartyRelationship`: Identifies `Friendship` as a Party Relationship.
