# Simple Contact List Example

This example demonstrates a **simplified** usage of the **Party Archetype Pattern**.

## Business Context
A basic address book or contact list where only individual people are stored with their phone numbers.

## Archetype Usage
In this example, the **Party Archetype Pattern** is applied in its most simplified form. To minimize complexity and remove unnecessary boilerplate for a simple use case:

1. **SimpleContact** realizes **Person** and **Party**.

This demonstrates that for simple domains, the archetype pattern doesn't necessarily require a complex class hierarchy. `SimpleContact` itself acts as both the domain entity and the concrete representation of a `Person` (and thus a `Party`).

It **does not** use separate classes for:
- `Party`
- `Person`
- `Organization`
- `OrganizationUnit`
- `Address` (Address records)
- `PartyAuthentication`

## Justification of Usage
In many basic applications, the full architectural separation of a Party Archetype is not required. When the system only needs to store personal contacts without complex organizational structures, identification documents, or authentication mechanisms, having a domain class that realizes the archetype is sufficient. 

**Why this is enough:**
- **Extreme Simplicity:** `SimpleContact` realizing `Person` and `Party` removes layers of abstraction that provide no value in this context, while still maintaining the conceptual alignment with the pattern.
- **Goal Achievement:** The primary goal is to represent a person with a phone number, which is fully addressed by the consolidated model.
- **Stable Identification:** Using a `username` field that realizes `PartyIdentifier` provides a unique and stable handle for the contact without the need for a dedicated `PartyIdentifier` class.
- **Maintainability:** The codebase is as minimal as possible, making it extremely easy to understand.
- **Compatibility:** The conceptual model still follows the Party archetype. If requirements grow, it can be easily refactored back into a separate hierarchy.

**Omission of Optional Parts:**
- **Address:** Often present in contact lists, but omitted here for extreme simplicity.
- **PartyAuthentication:** No security or login requirements in this narrow use case.

## Technical Implementation
- **Self-Contained**: This example is completely independent. While other examples copy archetype classes from `archetype-models`, this one is so simple that `SimpleContact` realizes both `Person` and `Party`.
- `@ArchetypeParty.Person`: Identifies `SimpleContact` as a Person within the Party archetype pattern.
- `@ArchetypeParty.PartyIdentifier`: Marks the `username` field as the unique identifier for the Party, demonstrating that the archetype's mandatory components can be realized as simple fields.
