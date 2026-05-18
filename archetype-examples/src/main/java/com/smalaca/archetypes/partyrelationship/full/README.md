# Full Organizational Relationship Example

This example demonstrates the **full** usage of the **PartyRelationship Archetype Pattern**.

## Business Context
Modeling complex relationships between organizations and people, such as employment, where specific roles and business rules (constraints) are required.

## Archetype Usage
The example uses all core parts of the PartyRelationship archetype:

1. **PartyRelationship**: `OrganizationalRelationship` represents the connection between parties.
2. **RelationshipType**: Defines the nature of the relationship (e.g., "Employment").
3. **PartyRole**: Represents how a party participates in a relationship.
4. **RoleType**: Defines the capacity of the role (e.g., "Employer", "Employee").
5. **RelationshipConstraint**: `OrganizationalHierarchyConstraint` ensures that an employment relationship must have at least one Organization as an "Employer" and one Person as an "Employee".
6. **Party, Person, Organization**: Supporting archetypes for participants.

## Justification of Usage
In enterprise systems, relationships are seldom as simple as a direct link. They involve multiple parties playing specific roles, governed by complex business rules.

**Why this is enough:**
- **Completeness:** It utilizes every component of the PartyRelationship archetype.
- **Enforcement of Rules:** The use of `RelationshipConstraint` demonstrates how to embed business logic into the architectural pattern.
- **Flexibility:** New roles or constraints can be added without changing the base relationship structure.

## Technical Implementation
- **Self-Contained**: This example is completely independent. It includes its own versions of archetype base classes (`Party`, `PartyRelationship`, etc.) to remain decoupled.
- `@ArchetypePartyRelationship.PartyRelationship`: Annotates `OrganizationalRelationship`.
- `@ArchetypePartyRelationship.RelationshipConstraint`: Annotates `OrganizationalHierarchyConstraint`.
