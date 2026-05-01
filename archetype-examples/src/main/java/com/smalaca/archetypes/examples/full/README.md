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

## Technical Implementation
- `@Archetype(name = "Party")`: Annotates classes that belong to the Party archetype.
- `@ArchetypePart(archetype = "Party", part = "...")`: Specific parts of the archetype used in fields or methods.

The `ClientManagementService` shows how these parts are orchestrated to fulfill business requirements.
