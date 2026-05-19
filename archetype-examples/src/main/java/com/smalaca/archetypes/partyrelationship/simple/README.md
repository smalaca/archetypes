# Simple Friendship Example

This example demonstrates a **simplified** usage of the **PartyRelationship Archetype Pattern**.

## Business Context
A basic social network where two people can be friends.

## Archetype Usage
In this example, the **PartyRelationship Archetype Pattern** is applied in its most simplified form:
1. **Friendship** realizes **PartyRelationship** with a fixed `RelationshipType` of "Friendship".
2. **FriendRole** realizes **PartyRole** with a fixed `RoleType` of "Friend", holding a person represented as a String.

It **does not** use separate classes for:
- `RelationshipConstraint`
- `Party` (Persons are represented as simple Strings)
- `PartyIdentifier`

## Justification of Usage
For a simple friendship relationship where roles are symmetric (both are "friends"), a consolidated model is sufficient.

**Why this is enough:**
- **Simplicity:** `Friendship` directly captures the relationship between two entities using inline `FriendRole` instances.
- **Goal Achievement:** The primary goal is to represent a link between two people with their roles.
- **Mandatory Coverage:** All mandatory parts (`PartyRelationship`, `RelationshipType`, `PartyRole`, `RoleType`, `Party`) are present, even if realized as simple fields or Strings.

**Omission of Optional Parts:**
- **RelationshipConstraint:** No business rules are required for a simple friendship.
- **PartyIdentifier:** No unique identification is needed in this narrow use case.

## Technical Implementation
- **Self-Contained**: This example is completely independent.
- `@ArchetypePartyRelationship.PartyRelationship`: Identifies `Friendship` as a Party Relationship.
- `@ArchetypePartyRelationship.RelationshipType`: Marks the `relationshipType` field.
- `@ArchetypePartyRelationship.PartyRole`: Marks `FriendRole` class and the `friend1`/`friend2` fields.
- `@ArchetypePartyRelationship.RoleType`: Marks the `roleType` field inside `FriendRole`.
- `@ArchetypePartyRelationship.Party`: Marks the `person` field inside `FriendRole`.
