# Enterprise Client Management Example

This example demonstrates the **full** usage of the **Party Archetype Pattern** in a B2B context.

## Business Context
In this scenario, we model an enterprise client management system where a company (Enterprise Client) interacts with our system. 

## Archetype Usage
The example uses all core parts of the Party archetype:

1. **Party**: The base for all participants.
2. **EnterpriseClient** realizes **Organization**.
3. **ClientContact** realizes **Person** (the person responsible for the account).
4. **ClientBranch** realizes **OrganizationUnit** to model the client's internal structure.
5. **Address**: Used to store physical locations for branches.
6. **PartyIdentifier**: Used to store official identifiers like Tax IDs.
7. **PartyAuthentication**: Used to manage secure access for contacts.
8. **Value Objects**: Dedicated VOs (`VatNumber`, `BranchCode`, `Username`) provide type safety for domain-specific identifiers.

## Justification of Usage
Enterprise systems often require a 360-degree view of a client. This includes knowing who they are (EnterpriseClient), how they are structured (ClientBranch), who to talk to (ClientContact), and how to verify their identity (PartyAuthentication/PartyIdentifier).

**Why this is enough:**
- **Completeness:** By utilizing the full breadth of the Party Archetype, we can capture every facet of a business relationship within a single, coherent architectural pattern.
- **Consistency:** All entities, whether they are people or organizations, share the same underlying logic for identifiers, addresses, and authentication. This reduces code duplication in the `ClientManagementService`.
- **Extensibility:** The "Full" approach provides a robust foundation. If the system later needs to support different types of clients (e.g., individual consultants), the existing `Person` and `Organization` archetypes are already in place and ready to be reused.

## Technical Implementation
- **Self-Contained**: This example is completely independent of the `archetype-models` module. It includes all necessary archetype classes (`Party`, `Person`, `Organization`, `Address`, etc.) within its own package to show a full-scale, decoupled usage.
- `@ArchetypeParty`: Marks the service as a manager of the Party archetype.
- `@ArchetypeParty.Organization`: Annotates `Organization` and `EnterpriseClient`.
- `@ArchetypeParty.Person`: Annotates `Person` and `ClientContact`.
- `@ArchetypeParty.OrganizationUnit`: Annotates `OrganizationUnit` and `ClientBranch`.
- `@ArchetypeParty.PartyIdentifier`: Annotates `VatNumber`, `Username`, and `BranchCode` to provide domain-level stable identifiers.
- `@ArchetypeParty.Address`, `@ArchetypeParty.PartyIdentifier`, `@ArchetypeParty.PartyAuthentication`: Mark service methods that handle these specific archetype parts.

The `ClientManagementService` shows how these parts are orchestrated to fulfill business requirements.
