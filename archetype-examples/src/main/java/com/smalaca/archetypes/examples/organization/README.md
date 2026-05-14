# Corporate Hierarchy Example

This example demonstrates the usage of the **Party Archetype Pattern** focused on **Organization** structures.

## Business Context
Modeling a large corporation with multiple departments. This highlights the recursive nature of organizations and organization units.

## Archetype Usage
This example focuses on the organizational aspects of the Party archetype:

1. **CorporateEntity** realizes **Organization**.
2. **Department** realizes **OrganizationUnit**.

The `Organization` archetype allows for a hierarchical structure where an organization can contain multiple `OrganizationUnit`s. This implementation demonstrates **recursive hierarchy** at the domain level, allowing departments to contain sub-departments.

This example **does not** use:
- `Person`
- `Address`
- `PartyIdentifier` (Official archetype records)
- `PartyAuthentication`

## Justification of Usage
In systems focused on resource management, logistics, or internal HR, the primary concern is the structure of the business itself. Modeling these as part of the Party Archetype ensures that departments and corporate entities share a common interface while maintaining their specific hierarchy.

**Why this is enough:**
- **Structural Integrity:** The `Organization` and `OrganizationUnit` models are specifically designed to handle parent-child relationships, which is the core requirement for modeling a corporation.
- **Domain-Driven API:** The domain classes (`CorporateEntity`, `Department`) encapsulate the archetype components and provide high-level methods like `addDepartment()` and `addSubDepartment()`, making the pattern easier to use without exposing its internal complexity.
- **Interchangeability:** By using the archetype, the system can treat a `CorporateEntity` and a `Department` uniformly where their common "Party" nature matters (e.g., both could be assigned an Address or a Tax ID).
- **Separation of Concerns:** This example intentionally excludes `Person` or `Authentication` to keep the focus on the complex organizational tree, proving that the archetype supports modular implementation of its parts.

**Omission of Mandatory Parts:**
- **None**: Every mandatory part is now realized. While `CorporateEntity` and `Department` use domain-specific fields like `taxIdentifier` and `departmentCode`, these are annotated with `@ArchetypeParty.PartyIdentifier` to explicitly realize the archetype's mandatory identifier requirement.

## Technical Implementation
- **Self-Contained**: This example is independent of the `archetype-models` module. It contains its own copies of `Organization`, `OrganizationUnit`, and `Party` to remain completely decoupled.
- `@ArchetypeParty.Organization`: Identifies `CorporateEntity` as an Organization.
- `@ArchetypeParty.OrganizationUnit`: Identifies `Department` as an Organization Unit.
- `@ArchetypeParty.PartyIdentifier`: Applied to `taxIdentifier` and `departmentCode` to mark them as stable identifiers for the respective parties.
