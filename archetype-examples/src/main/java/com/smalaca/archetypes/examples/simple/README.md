# Simple Contact List Example

This example demonstrates a **simplified** usage of the **Party Archetype Pattern**.

## Business Context
A basic address book or contact list where only individual people are stored with their phone numbers.

## Archetype Usage
This example only uses a subset of the Party archetype:

1. **Party**: Base class.
2. **Person**: Represented by `SimpleContact`.

It **does not** use:
- `Organization`
- `OrganizationUnit`
- `Address` (Address records)
- `PartyIdentifier`
- `PartyAuthentication`

This shows that the archetype can be used partially when the domain complexity is low.

## Technical Implementation
- `@Archetype(name = "Party")`: Marks the `SimpleContact` as part of the Party pattern.
- `@ArchetypePart(archetype = "Party", part = "Person")`: Specifically identifies it as a Person archetype.
