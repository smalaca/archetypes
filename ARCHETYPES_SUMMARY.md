# Archetype Patterns Implementation Summary

This document outlines how archetype patterns are implemented and presented in this repository. It serves as a foundation for understanding the current `Party` archetype implementation and as a template for introducing subsequent archetypes.

## 1. Goal of the Modules

The project is structured into three distinct modules, each with a specific responsibility:

*   **`archetype-annotations`**: Contains the meta-data (Java Annotations) used to explicitly link domain code to archetype concepts. This provides a "machine-readable" bridge between the domain model and the archetype meta-model.
*   **`archetype-models`**: Contains the "Canonical Model" or "Meta-Model" of the archetype. These are pure, decoupled, and highly reusable classes that follow the pattern exactly as described in the literature. They serve as a reference point.
*   **`archetype-examples`**: Contains various ways of applying the archetype to specific business scenarios. It demonstrates that archetypes are not rigid templates but flexible patterns that can be adapted based on business needs.

## 2. Visualization via Annotations

The `@ArchetypeParty` annotation (and its nested annotations like `.Person`, `.Organization`, `.PartyIdentifier`) is the primary tool for visualizing the archetype within the code.

*   **Explicit Mapping**: Annotations mark classes, fields, and even service methods to show exactly which "part" of the archetype they realize.
*   **Service Layer Alignment**: In the `full` example, methods in `ClientManagementService` are annotated to show how business operations (e.g., `registerBranch`) map to archetype transitions (adding an `OrganizationUnit`).
*   **Documentation in Code**: For developers, the annotations act as a form of "live documentation" that survives refactoring.

## 3. Implementation Variants (The Three Examples)

To show the flexibility of archetype patterns, the `party` example is implemented in three different ways:

### A. Simple Example (`simple` package)
*   **Approach**: Collapsed/Merged.
*   **Decision**: For very simple domains (e.g., a basic contact list), the entire archetype hierarchy is collapsed into a single class (`SimpleContact`).
*   **Why**: To show that archetypes don't have to be complex. If you only have people, you don't need a `Party` base class or a `Person` abstraction.

### B. Organization Example (`organization` package)
*   **Approach**: Recursive & Simplified.
*   **Decision**: Merged `Organization` with `Party` and `Department` with `OrganizationUnit`.
*   **Why**: Focused on the recursive nature of corporate hierarchies. By merging the archetype base classes into the domain entities, we reduce boilerplate while maintaining the structural integrity (Composite pattern) required for hierarchical departments.

### C. Full Example (`full` package)
*   **Approach**: Comprehensive & Inheritance-based.
*   **Decision**: 
    *   Uses a base `Party` class.
    *   Entities like `EnterpriseClient` and `ClientBranch` extend `Organization`.
    *   Authentication is moved to `ClientContact` (1:1 relation) because it's a personal attribute in this context.
    *   Dedicated Value Objects (`VatNumber`, `BranchCode`, `ClientContactName`) are used for identifiers.
*   **Why**: To demonstrate how the full pattern handles complex B2B scenarios with multi-level hierarchies, multiple addresses, and security requirements.

## 4. Key Architectural Decisions

1.  **Merging vs. Composition**: In earlier iterations, composition was used (entities *had* an Organization). It was moved to inheritance (entities *are* Organizations) to simplify the domain model and reduce delegation code when deep extensibility isn't a requirement.
2.  **Dedicated Value Objects**: Instead of a generic `PartyIdentifier` class with a `type` string, we use specific types like `TaxIdentifier`. This provides:
    *   Type safety in method signatures.
    *   A natural place for specific validation logic.
    *   Better domain clarity.
3.  **Self-Containment**: Each example is self-contained and does not depend on `archetype-models`. This simulates how a real microservice or module would "absorb" the archetype pattern into its own domain.

## 5. Testing Methodology

The repository emphasizes a **Domain-Specific Language (DSL)** approach to testing.

*   **Custom Assertions**: Every major entity has a corresponding `Assert` class (using AssertJ).
    *   Example: `EnterpriseClientAssert` provides methods like `hasBranch()` or `hasAddressIn()`.
*   **Exhaustive Validation**: Tests are designed to validate **every field** of the created objects. This ensures that the mapping to the archetype doesn't accidentally drop data.
*   **Scenarios vs. Units**: Tests cover both positive and negative scenarios (e.g., branches with vs. without addresses) to verify that the archetype structure handles edge cases correctly.
*   **Separation of Concerns**: Organizational structures (branches) are tested separately from personal details (contacts) to keep test classes focused.

## 6. Template for Future Archetypes

When introducing a new archetype (e.g., `Product` or `Order`), the following steps should be followed:
1.  **Define Annotations**: Create `@Archetype[Name]` in `archetype-annotations`.
2.  **Define Meta-Model**: Implement the pure pattern in `archetype-models`.
3.  **Create Examples**: Provide at least two variants:
    *   A **Simplified** version showing the minimal viable implementation.
    *   A **Full** version showing how it handles complex business rules and relationships.
4.  **Implement Custom Assertions**: Ensure that the example is accompanied by a robust assertion DSL for comprehensive validation.
