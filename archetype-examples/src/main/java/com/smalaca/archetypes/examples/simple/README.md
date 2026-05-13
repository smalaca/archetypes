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

## Justification of Usage
In many basic applications, the full complexity of a Party Archetype is not required. When the system only needs to store personal contacts without complex organizational structures, identification documents, or authentication mechanisms, using only the `Person` part of the archetype is sufficient. 

**Why this is enough:**
- **Goal Achievement:** The primary goal is to represent a person with a phone number, which is fully addressed by the `Person` model.
- **Maintainability:** By avoiding unused components (like `OrganizationUnit` or `Address` records), the codebase remains clean and easy to understand.
- **Compatibility:** Even with this minimal setup, the system remains "Party-compatible." If requirements grow (e.g., adding corporate contacts), the underlying archetype allows for seamless expansion without refactoring existing simple contacts.

## Technical Implementation
- `@ArchetypeParty.Person`: Identifies `SimpleContact` as a Person within the Party archetype pattern via composition.
