# Corporate Hierarchy Example

This example demonstrates the usage of the **Party Archetype Pattern** focused on **Organization** structures.

## Business Context
Modeling a large corporation with multiple departments. This highlights the recursive nature of organizations and organization units.

## Archetype Usage
This example focuses on the organizational aspects of the Party archetype:

1. **CorporateEntity** extends **Organization**.
2. **OrganizationUnit** represents a part of the organization.

The `Organization` archetype allows for a hierarchical structure where an organization can contain multiple `OrganizationUnit`s. This implementation demonstrates **recursive hierarchy** at the domain level, allowing units to contain sub-units.

This example **does not** use:
- `Person`
- `Address`
- `PartyAuthentication`

## Justification of Usage
In systems focused on resource management, logistics, or internal HR, the primary concern is the structure of the business itself. Modeling these as part of the Party Archetype ensures that departments and corporate entities share a common interface while maintaining their specific hierarchy.

**Why this is enough:**
- **Structural Integrity:** The `Organization` and `OrganizationUnit` models are specifically designed to handle parent-child relationships, which is the core requirement for modeling a corporation.
- **Simplified Domain Model:** By merging `Organization` with `Party` and `Department` with `OrganizationUnit`, we reduce complexity while maintaining the archetype's core structure. This is justified by the lack of need for additional extensibility in this specific use case.
- **Value Objects:** Using dedicated Value Objects (e.g., `TaxIdentifier`, `DepartmentCode`) ensures type safety and provides a place for identifier-related validation logic.
- **Interchangeability:** By using the archetype, the system can treat a `CorporateEntity` and an `OrganizationUnit` uniformly where their common "Organization" nature matters.

**Omission of Mandatory Parts:**
- **None**: Every mandatory part is now realized. `CorporateEntity` and `OrganizationUnit` use dedicated Value Objects, which are annotated with `@ArchetypeParty.PartyIdentifier` to explicitly realize the archetype's mandatory identifier requirement.

## Technical Implementation
- **Self-Contained**: This example is independent of the `archetype-models` module. It contains its own copies of `Organization`, `OrganizationUnit`, and dedicated Value Objects (`TaxIdentifier`, `DepartmentCode`) to remain completely decoupled.
- `@ArchetypeParty.Organization`: Identifies `Organization` and `CorporateEntity` as Organizations.
- `@ArchetypeParty.OrganizationUnit`: Identifies `OrganizationUnit`.
- `@ArchetypeParty.PartyIdentifier`: Applied to the `TaxIdentifier` and `DepartmentCode` classes and their usages to mark stable identifiers.
