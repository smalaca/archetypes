# Party Archetype Pattern: Examples Usage Summary

This document provides a comprehensive overview of how the **Party Archetype Pattern** is applied across the different example modules, highlighting which parts are used and justifying any deviations from the pattern's mandatory requirements.

## Comparison of Archetype Parts Usage

| Archetype Part | Simple Example | Organization Example | Full Example |
| :--- | :---: | :---: | :---: |
| **Party** (Base) | ✓ | ✓ | ✓ |
| **Person** | ✓ | - | ✓ |
| **Organization** | - | ✓ | ✓ |
| **PartyIdentifier** | realized by username | realized by domain fields | ✓ (and domain fields) |
| **OrganizationUnit** | - | ✓ | ✓ |
| **Address** | - | - | ✓ |
| **PartyAuthentication** | - | - | ✓ |

## Analysis of Omissions (Mandatory Parts)

The Party Archetype Pattern defines `Party`, (`Person` OR `Organization`), and `PartyIdentifier` as mandatory components. While all examples implement the base `Party` and at least one concrete type, some intentionally omit the formal `PartyIdentifier` archetype.

### Simple Example
- **Realized Mandatory Part:** `PartyIdentifier` realized by `username`.
- **Reasoning:** Designed for extreme simplicity (e.g., a basic contact list).
- **Justification:** In this narrow context, a `username` field that realizes `PartyIdentifier` provides a unique and stable handle for the contact without the need for a dedicated `PartyIdentifier` class.

### Corporate Hierarchy Example
- **Realized Mandatory Part:** `PartyIdentifier` realized by domain fields.
- **Reasoning:** Focuses on the recursive organizational structure.
- **Justification:** While the `CorporateEntity` and `Department` include domain-specific fields like `taxIdentifier` and `departmentCode`, these are annotated with `@ArchetypeParty.PartyIdentifier` to explicitly realize the archetype's mandatory identifier requirement without the need for the formal `PartyIdentifier` archetype class.

### Full Example
- **Omitted Mandatory Part:** None
- **Reasoning:** Demonstrates a complete, enterprise-grade implementation.
- **Justification:** This example utilizes every part of the archetype, including the formal `PartyIdentifier`, to provide a 360-degree view of the party, ensuring maximum architectural consistency and flexibility.

## Conclusion

The examples demonstrate that while the Party Archetype Pattern provides a robust blueprint, it is not a rigid cage. The "mandatory" parts represent the ideal for a fully-featured system, but they can be pragmatically omitted or simplified in specific, low-complexity contexts without losing the overall benefits of the pattern's structure.
