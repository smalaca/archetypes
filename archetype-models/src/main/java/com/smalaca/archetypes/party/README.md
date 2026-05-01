# Party Archetype Pattern

## Purpose
The **Party Archetype Pattern** represents an identifiable, addressable unit that has some autonomous control over its actions and may have legal status. It is a fundamental pattern for modeling people and organizations in a uniform way, allowing systems to treat them interchangeably where appropriate (e.g., both a person and a company can be a "customer" or a "contractor").

## Parties (Core Archetypes)
- **Party:** An abstract base class for `Person` and `Organization`. It manages common attributes like identifiers, addresses, and authentications.
- **Person:** Represents an individual human being.
- **Organization:** Represents a group of people or other organizations (e.g., a corporation, a department).
- **OrganizationUnit:** Represents a specific part of an `Organization`. It allows for modeling complex, recursive organizational hierarchies.
- **Address:** A distinct archetype for locating and contacting parties (physical address, email, etc.).
- **PartyIdentifier:** A unique identifier for a party, such as a SSN, Tax ID, or Passport Number.
- **PartyAuthentication:** Mechanisms used to verify the identity of a party (e.g., login credentials, digital certificates).

## Dependency Diagram

```mermaid
classDiagram
    class Party {
        <<abstract>>
        -List~PartyIdentifier~ identifiers
        -List~Address~ addresses
        -List~PartyAuthentication~ authentications
    }
    class Person {
        -String firstName
        -String lastName
    }
    class Organization {
        -String name
        -List~OrganizationUnit~ units
    }
    class OrganizationUnit {
        -String name
    }
    class Address {
        <<record>>
        -String street
        -String city
        -String postalCode
        -String country
    }
    class PartyIdentifier {
        <<record>>
        -String identifier
        -String type
    }
    class PartyAuthentication {
        <<record>>
        -String type
        -String secret
    }

    Party <|-- Person
    Party <|-- Organization
    Organization "1" *-- "0..*" OrganizationUnit
    Party "1" *-- "0..*" Address
    Party "1" *-- "0..*" PartyIdentifier
    Party "1" *-- "0..*" PartyAuthentication
```
