# Full Organizational Relationship Example

This example demonstrates the **full** usage of the **PartyRelationship Archetype Pattern**.

## Business Context
Modeling complex relationships between organizations and people, such as employment, where specific roles and business rules (constraints) are required.

## Archetype Usage
The example uses **all** parts of the PartyRelationship archetype — both mandatory and optional:

### Mandatory Parts Used
1. **PartyRelationship**: `OrganizationalRelationship` (extends `PartyRelationship`) represents the connection between parties.
2. **RelationshipType**: `RelationshipType` record defines the nature of the relationship (e.g., "Employment").
3. **PartyRole**: `PartyRole` links a `Party` to a `RoleType` within a relationship.
4. **RoleType**: `RoleType` record defines the capacity of the role (e.g., "Employer", "Employee").
5. **Party**: `Party` abstract class with `Person` and `Organization` as concrete implementations.

### Optional Parts Used
6. **RelationshipConstraint**: `OrganizationalHierarchyConstraint` ensures that an employment relationship must have at least one `Organization` as "Employer" and one `Person` as "Employee".
7. **PartyIdentifier**: `PartyIdentifier` record provides unique identification for parties.

## Justification of Usage
In enterprise systems, relationships are seldom as simple as a direct link. They involve multiple parties playing specific roles, governed by complex business rules.

**Why this is complete:**
- **Completeness:** It utilizes every component of the PartyRelationship archetype, both mandatory and optional.
- **Enforcement of Rules:** The use of `RelationshipConstraint` demonstrates how to embed business logic into the architectural pattern.
- **Flexibility:** New roles or constraints can be added without changing the base relationship structure.

## Technical Implementation
- **Self-Contained**: This example is completely independent. It includes its own versions of archetype base classes (`Party`, `PartyRelationship`, etc.) to remain decoupled.
- `@ArchetypePartyRelationship.PartyRelationship`: Annotates `PartyRelationship` and `OrganizationalRelationship`.
- `@ArchetypePartyRelationship.RelationshipType`: Annotates `RelationshipType`.
- `@ArchetypePartyRelationship.PartyRole`: Annotates `PartyRole`.
- `@ArchetypePartyRelationship.RoleType`: Annotates `RoleType`.
- `@ArchetypePartyRelationship.Party`: Annotates `Party`.
- `@ArchetypePartyRelationship.PartyIdentifier`: Annotates `PartyIdentifier`.
- `@ArchetypePartyRelationship.RelationshipConstraint`: Annotates `OrganizationalHierarchyConstraint`.
