# Simple Personal Relationships Example

This example demonstrates a **simplified** usage of the **PartyRelationship Archetype Pattern** with multiple relationship types.

## Business Context
A basic social/family network where people can be connected through different types of personal relationships: **Friendship**, **Marriage**, and **Family**.

Each party is identified by a `partyId` (a unique string identifier), which serves as the `@Party` marker — the minimal form of party identification without a full `Party` hierarchy.

## Relationship Types

### 1. Friendship
Two people who know each other socially. The relationship is **symmetric** — both sides play the same role (`FriendRole`).

### 2. Marriage
Two people joined in marriage. The relationship is **symmetric** — both sides play the same role (`SpouseRole`).

### 3. Family
A family unit with **asymmetric, many-to-many roles**: parents (`ParentRole`) and children (`ChildRole`). Both sides can have multiple members.

## Archetype Usage
In this example, the **PartyRelationship Archetype Pattern** is applied in its most simplified form:
1. **Friendship**, **Marriage**, **Family** each realize **PartyRelationship** with a fixed `RelationshipType`.
2. **FriendRole**, **SpouseRole**, **ParentRole**, **ChildRole** each realize **PartyRole** with a fixed `RoleType`.
3. Each role holds a `partyId` (String) as the `@Party` — the unique identifier of the party in the relationship.

It **does not** use separate classes for:
- `RelationshipConstraint`
- A full `Party` hierarchy (parties are identified by a simple `partyId` String)
- `PartyIdentifier` (the `partyId` field itself serves as the identifier)

## What Makes This "Simple"
- **partyId as identity:** Each role carries a `partyId` String that uniquely identifies the party — no full `Party` class hierarchy needed.
- **Fixed role types:** Role types are fixed strings defined in each role class — no separate `RoleType` value object needed.
- **Self-descriptive class names:** `Friendship`, `Marriage`, `Family` communicate the relationship type — no separate `RelationshipType` class needed.
- **Multiple relationship types:** Three distinct relationship types are modeled, each with their own dedicated role classes.
- **Multiple party roles:** Different relationships require different roles — `FriendRole`, `SpouseRole`, `ParentRole`, `ChildRole`.

## Justification of Usage
For personal relationships where roles are well-understood and fixed, a consolidated model is sufficient.

**Why this is enough:**
- **Simplicity:** Each relationship class directly captures the connection using dedicated role instances.
- **Mandatory Coverage:** All mandatory parts (`PartyRelationship`, `RelationshipType`, `PartyRole`, `RoleType`, `Party`) are present, realized as simple fields and strings.
- **Multiple types:** Three relationship types demonstrate that the simplified pattern scales across a family of related concepts.

**Omission of Optional Parts:**
- **RelationshipConstraint:** No business rules are required for these personal relationships.
- **PartyIdentifier:** The `partyId` field on each role serves as the minimal party identifier.

## Technical Implementation
- **Self-Contained**: This example is completely independent.
- `@ArchetypePartyRelationship.PartyRelationship`: Identifies `Friendship`, `Marriage`, `Family` as Party Relationships.
- `@ArchetypePartyRelationship.RelationshipType`: Marks the `relationshipType` field in each relationship class.
- `@ArchetypePartyRelationship.PartyRole`: Marks role classes (`FriendRole`, `SpouseRole`, `ParentRole`, `ChildRole`) and the role fields/lists in each relationship class.
- `@ArchetypePartyRelationship.RoleType`: Marks the `roleType` field inside each role class.
- `@ArchetypePartyRelationship.Party`: Marks the `partyId` field inside each role class.

## Class Structure

```
Friendship                  ← @PartyRelationship
  relationshipType: String  ← @RelationshipType (fixed: "Friendship")
  friend1: FriendRole       ← @PartyRole
  friend2: FriendRole       ← @PartyRole

FriendRole                  ← @PartyRole
  roleType: String          ← @RoleType (fixed: "Friend")
  partyId: String           ← @Party
  person: String

Marriage                    ← @PartyRelationship
  relationshipType: String  ← @RelationshipType (fixed: "Marriage")
  spouse1: SpouseRole       ← @PartyRole
  spouse2: SpouseRole       ← @PartyRole

SpouseRole                  ← @PartyRole
  roleType: String          ← @RoleType (fixed: "Spouse")
  partyId: String           ← @Party
  person: String

Family                      ← @PartyRelationship
  relationshipType: String  ← @RelationshipType (fixed: "Family")
  parents: List<ParentRole> ← @PartyRole
  children: List<ChildRole> ← @PartyRole

ParentRole                  ← @PartyRole
  roleType: String          ← @RoleType (fixed: "Parent")
  partyId: String           ← @Party
  person: String

ChildRole                   ← @PartyRole
  roleType: String          ← @RoleType (fixed: "Child")
  partyId: String           ← @Party
  person: String
```
