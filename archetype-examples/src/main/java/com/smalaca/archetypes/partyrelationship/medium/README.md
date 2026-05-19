# Medium Complexity Employment Example

This example demonstrates a **medium complexity** usage of the **PartyRelationship Archetype Pattern**.

## Business Context
An employment relationship where one employer (organization name as String) has multiple employees with specific positions.

## Archetype Usage
In this example, the **PartyRelationship Archetype Pattern** is applied with a focus on roles:
1. **Employment** realizes **PartyRelationship** with a fixed `RelationshipType` of "Employment".
2. **EmployerRole** realizes **PartyRole** with a fixed `RoleType` of "Employer".
3. **EmployeeRole** (inner record) realizes **PartyRole** with a `position` field realizing `RoleType`.

It **does not** use separate classes for:
- `RelationshipConstraint`
- `Party` (Parties are represented as simple Strings)
- `PartyIdentifier`

## Justification of Usage
In many HR or project management systems, we need to track who works for whom and in what capacity.

**Why this is enough:**
- **Mandatory Coverage:** All mandatory parts (`PartyRelationship`, `RelationshipType`, `PartyRole`, `RoleType`, `Party`) are present, even if realized as simple fields or Strings.
- **Role Awareness:** It explicitly models `EmployerRole` and `EmployeeRole`, allowing for additional attributes like `position`.
- **One-to-Many Relationship:** It demonstrates that a `PartyRelationship` can involve multiple roles.

**Omission of Optional Parts:**
- **RelationshipConstraint:** No business rules enforcement is required in this use case.
- **PartyIdentifier:** No unique identification is needed in this use case.

## Technical Implementation
- **Self-Contained**: This example is completely independent.
- `@ArchetypePartyRelationship.PartyRelationship`: Identifies `Employment` as a Party Relationship.
- `@ArchetypePartyRelationship.RelationshipType`: Marks the `relationshipType` field.
- `@ArchetypePartyRelationship.PartyRole`: Identifies `EmployerRole` and `EmployeeRole` as Party Roles, and marks the `employer`/`employees` fields.
- `@ArchetypePartyRelationship.RoleType`: Marks the `roleType` field in `EmployerRole` and the `position` field in `EmployeeRole`.
- `@ArchetypePartyRelationship.Party`: Marks the `name` field in both `EmployerRole` and `EmployeeRole`.
