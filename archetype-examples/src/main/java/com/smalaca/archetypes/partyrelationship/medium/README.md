# Medium Complexity Employment Example

This example demonstrates a **medium complexity** usage of the **PartyRelationship Archetype Pattern**.

## Business Context
An employment relationship where one employer (organization name as String) has multiple employees with specific positions.

## Archetype Usage
In this example, the **PartyRelationship Archetype Pattern** is applied with a focus on roles:

1. **Employment** realizes **PartyRelationship**.
2. **EmployeeRole** (inner record) realizes **PartyRole**.

It **does not** use separate classes for:
- `RoleType` (position in `EmployeeRole` implicitly represents the role type)
- `RelationshipType`
- `RelationshipConstraint`
- `Party` (Parties are represented as simple Strings)

## Justification of Usage
In many HR or project management systems, we need to track who works for whom and in what capacity.

**Why this is enough:**
- **Role Awareness:** It explicitly models the `EmployeeRole`, allowing for additional attributes like `position`.
- **One-to-Many Relationship:** It demonstrates that a `PartyRelationship` can involve multiple roles.
- **Simplified Domain Model:** By using Strings for parties and omitting formal `RoleType` and `RelationshipType` objects, it remains easy to use while still following the archetype's core structure.

## Technical Implementation
- **Self-Contained**: This example is completely independent.
- `@ArchetypePartyRelationship.PartyRelationship`: Identifies `Employment` as a Party Relationship.
- `@ArchetypePartyRelationship.PartyRole`: Identifies `EmployeeRole` as a Party Role.
