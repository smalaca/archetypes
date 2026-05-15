# Enterprise Client Management Example

This example demonstrates the **full** usage of the **Party Archetype Pattern** in a B2B context.

## Business Context
In this scenario, we model an enterprise client management system where a company (Enterprise Client) interacts with our system. 

## Archetype Usage
The example uses all core parts of the Party archetype:

1. **Party**: The base for all participants.
2. **EnterpriseClient** extends **Organization**.
3. **ClientContact** extends **Party** and realizes **Person**.
4. **ClientBranch** extends **Organization** and realizes **OrganizationUnit**.
5. **Address**: Used to store physical locations for branches.
6. **Value Objects**: Dedicated VOs (`VatNumber`, `BranchCode`, `ClientContactName`) provide type safety for domain-specific identifiers.
7. **ClientAuthentication** realizes **PartyAuthentication**: Used to manage secure access for contacts.

## Client Management Service
The `ClientManagementService` acts as a facade that orchestrates the Party archetype entities. It shows how to:
- Register a new branch (`ClientBranch`) with its branch code.
- Add an address (`Address`) to a party.
- Setup authentication (`ClientAuthentication`) for a client contact.

The service uses archetype annotations to explicitly mark which part of the archetype is being handled by each method, ensuring alignment with the Party Archetype pattern.

## Justification of Usage
Enterprise systems often require a 360-degree view of a client. This includes knowing who they are (`EnterpriseClient`), how they are structured (`ClientBranch`), who to talk to (`ClientContact`), and how to verify their identity (`ClientAuthentication`).

**Why this is enough:**
- **Simplified Structure:** By using inheritance (e.g., `EnterpriseClient` extends `Organization`) and merging generic archetype roles with domain entities, we reduce the number of classes and architectural complexity where deep extensibility is not required.
- **Completeness:** By utilizing the full breadth of the Party Archetype, we can capture every facet of a business relationship within a single, coherent architectural pattern.
- **Consistency:** All entities share the same underlying logic for identifiers, addresses, and authentication.
- **Value Objects:** Using dedicated Value Objects (e.g., `VatNumber`, `ClientContactName`) ensures type safety and encapsulates validation logic.

## Technical Implementation
- **Self-Contained**: This example is completely independent of the `archetype-models` module. It includes its own versions of archetype base classes (`Party`, `Organization`, etc.) to remain decoupled.
- `@ArchetypeParty`: Marks the `ClientManagementService` as the orchestrator of the Party archetype.
- `@ArchetypeParty.Organization`: Annotates `Organization` and `EnterpriseClient`.
- `@ArchetypeParty.Person`: Annotates `ClientContact`.
- `@ArchetypeParty.OrganizationUnit`: Annotates `ClientBranch`.
- `@ArchetypeParty.PartyIdentifier`: Annotates `VatNumber`, `BranchCode`, and `ClientContactName`.
- `@ArchetypeParty.Address`, `@ArchetypeParty.PartyAuthentication`, `@ArchetypeParty.OrganizationUnit`: Mark service methods that handle these specific archetype parts.
