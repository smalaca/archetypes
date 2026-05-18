# Enterprise Patterns and MDA: Archetype Patterns Summary

This document provides a short summary of the archetype patterns described in the book **"Enterprise Patterns and MDA: Building Better Software with Archetype Patterns and UML"** by Jim Arlow and Ila Neustadt. 

In the context of Advanced DDD (as seen in the SAGES training materials), these archetypes are often treated as **Meta Models**—reusable structural templates that provide a high-level abstraction for complex business domains.

## Archetype Pattern Catalog

### 1. Party Archetype Pattern
Represents an identifiable, addressable unit that has some autonomous control over its actions and may have legal status.
- **Key Concepts:** Persons and Organizations. The pattern explicitly distinguishes between different levels of organizational structure.
- **Core Archetypes:** `Party`, `Person`, `Organization`, `OrganizationUnit`, `PartyIdentifier`, `Address`, `PartyAuthentication`.
- **Granular Focus:** 
    - **Address:** Treated as a vital, distinct archetype for locating and contacting parties.
    - **Organization & OrganizationUnit:** Provides a recursive structure to model complex corporate hierarchies.

### 2. PartyRelationship Archetype Pattern
Describes the relationships between parties and the roles they play within those relationships.
- **Key Concepts:** Captures how parties interact (e.g., Employment, Peer-to-Peer).
- **Core Archetypes:** `PartyRole`, `RoleType`, `PartyRelationship`, `RelationshipType`, `RelationshipConstraint`.

### 3. Customer Relationship Management (CRM) Archetype Pattern
Extends the Party and PartyRelationship patterns to focus specifically on the interactions between a business and its customers.
- **Key Concepts:** Managing customer lifecycle, tracking interactions, and defining customer roles.
- **Core Archetypes:** `Customer`, `CustomerRole`, `CustomerGroup`.

### 4. Product Archetype Pattern
Models the "things" a business provides to its customers, including both physical goods and services.
- **Key Concepts:** Distinguishes between what a product is (Product Type) and the specific instance of it.
- **Core Archetypes:** `ProductType`, `ProductInstance`, `ServiceType`, `ServiceInstance`, `Package`.
- **Simplified Product Model:** Practical implementations often use a simplified version of the Product archetype to reduce complexity while maintaining the core Type/Instance distinction.

### 5. Inventory Archetype Pattern
Manages the stock or store of goods and the delivery of services.
- **Key Concepts:** Tracking availability, reservations, and capacity planning.
- **Core Archetypes:** `Inventory`, `InventoryEntry`, `ProductInventoryEntry`, `ServiceInventoryEntry`.

### 6. Order Archetype Pattern
Represents a formal request from a buyer to a seller to supply goods or services under specified terms.
- **Key Concepts:** Purchase orders and sales orders, order lines, and order statuses.
- **Core Archetypes:** `Order`, `OrderLine`, `PurchaseOrder`, `SalesOrder`.

### 7. Quantity Archetype Pattern
Provides a way to represent amounts of things measured according to a standard of measurement (metric).
- **Key Concepts:** Metrics, units, and systems of units.
- **Core Archetypes:** `Quantity`, `Metric`, `Unit`, `SystemOfUnits`.

### 8. Money Archetype Pattern
A specialized version of the Quantity pattern focused on financial values and currencies.
- **Key Concepts:** Currencies, exchange rates, and payments.
- **Core Archetypes:** `Money`, `Currency`, `ExchangeRate`, `Payment`.
- **Granular Focus:** 
    - **Payment:** Detailed as a specific transaction or allocation of money to satisfy an obligation.

### 9. Rule Archetype Pattern
Captures business logic and constraints that govern how the enterprise operates.
- **Key Concepts:** Rule evaluation context and rule elements.
- **Core Archetypes:** `Rule`, `RuleSet`, `RuleElement`, `RuleContext`.
- **Granular Focus:** 
    - **RuleSet:** Introduces the concept of grouping individual rules into a cohesive set that can be applied together in a specific context.
