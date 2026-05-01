# Corporate Hierarchy Example

This example demonstrates the usage of the **Party Archetype Pattern** focused on **Organization** structures.

## Business Context
Modeling a large corporation with multiple departments. This highlights the recursive nature of organizations and organization units.

## Archetype Usage
This example focuses on the organizational aspects of the Party archetype:

1. **Organization**: Represented by `CorporateEntity`.
2. **OrganizationUnit**: Represented by `Department`.

The `Organization` archetype allows for a hierarchical structure where an organization can contain multiple `OrganizationUnit`s (which are themselves a type of `Organization`).

## Technical Implementation
- `@Archetype(name = "Party")`: Marks the classes as part of the Party pattern.
- `@ArchetypePart(archetype = "Party", part = "Organization")`: Identifies the `CorporateEntity` as an Organization.
- `@ArchetypePart(archetype = "Party", part = "OrganizationUnit")`: Identifies the `Department` as an Organization Unit.
