# Enterprise Client Management Example

This example demonstrates the **full** usage of the **Party Archetype Pattern** in a B2B context.

## Business Context
In this scenario, we model an enterprise client management system where a company (Enterprise Client) interacts with our system. 

## Archetype Usage
The example uses all core parts of the Party archetype:

1. **Party**: The base for all participants.
2. **Organization**: Represented by `EnterpriseClient`.
3. **Person**: Represented by `ClientContact` (the person responsible for the account).
4. **OrganizationUnit**: Represented by `ClientBranch` to model the client's internal structure.
5. **Address**: Used to store physical locations for branches.
6. **PartyIdentifier**: Used to store official identifiers like Tax IDs.
7. **PartyAuthentication**: Used to manage secure access for contacts.

## Justification of Usage
Enterprise systems often require a 360-degree view of a client. This includes knowing who they are (EnterpriseClient), how they are structured (ClientBranch), who to talk to (ClientContact), and how to verify their identity (PartyAuthentication/PartyIdentifier).

**Why this is enough:**
- **Completeness:** By utilizing the full breadth of the Party Archetype, we can capture every facet of a business relationship within a single, coherent architectural pattern.
- **Consistency:** All entities, whether they are people or organizations, share the same underlying logic for identifiers, addresses, and authentication. This reduces code duplication in the `ClientManagementService`.
- **Extensibility:** The "Full" approach provides a robust foundation. If the system later needs to support different types of clients (e.g., individual consultants), the existing `Person` and `Organization` archetypes are already in place and ready to be reused.

## Technical Implementation
- `@ArchetypeParty`: Marks the service as a manager of the Party archetype.
- `@ArchetypeParty.Organization`: Annotates `EnterpriseClient`.
- `@ArchetypeParty.Person`: Annotates `ClientContact`.
- `@ArchetypeParty.OrganizationUnit`: Annotates `ClientBranch`.
- `@ArchetypeParty.Address`, `@ArchetypeParty.PartyIdentifier`, `@ArchetypeParty.PartyAuthentication`: Mark service methods that handle these specific archetype parts.

The `ClientManagementService` shows how these parts are orchestrated to fulfill business requirements.
