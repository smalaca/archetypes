[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=smalaca_archetypes&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=smalaca_archetypes)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=smalaca_archetypes&metric=coverage)](https://sonarcloud.io/summary/new_code?id=smalaca_archetypes)

# Archetypes

## Table of Contents
- [Goal of the repository](#goal-of-the-repository)
- [Learn More: Articles](#learn-more-articles)
- [Training Center Repository](#training-center-repository)
- [Annotations](#annotations)
- [Archetypes and Elements](#archetypes-and-elements)
  - [Party](#party)
  - [Product](#product)
  - [Catalog](#catalog)
  - [Inventory](#inventory)
  - [Availability](#availability)
  - [Order](#order)
  - [Rule](#rule)
- [Collaboration](#collaboration)

## Goal of the repository
The goal of this repository is to present implementations of archetypes using Domain-Driven Design (DDD) patterns. These archetypes are based on the book *"Enterprise Patterns and MDA: Building Better Software with Archetype Patterns and UML"* by Jim Arlow and Ila Neustadt.

## Learn More: Articles
If you want to dive deeper into the concept of archetypes and how they are applied in this repository, check out the following articles:
- **[Beyond DDD Building Blocks: Why You Need Archetypes](https://www.sebastianmalaca.com/beyond-ddd-building-blocks-why-you-need-archetypes/)** – An introduction to archetypes and why they are the missing blueprints in Domain-Driven Design.
- **[The Anatomy of the Party Archetype](https://www.sebastianmalaca.com/the-anatomy-of-the-party-archetype/)** – Learn how the Party archetype separates identity from behavior, enabling flexible models that support multiple roles and evolving business relationships.

## Training Center Repository
The `training-center` directory contains the core domain model implementation of a training center system.
- **Domain Model Only**: The code focuses strictly on the domain model implementation.
- **Mono-repository**: Top-level packages represent separate Bounded Contexts within the system.
- **Purpose**: While there is enough logic to demonstrate the archetypes in action, this is not intended to be a fully functional, production-ready training center application.

## Annotations
The repository uses custom annotations to categorize and document the implementation according to archetypes, architecture patterns, and Domain-Driven Design principles.
- **Archetype Annotations**: These annotations are used to identify the archetypes and their specific elements within the code. You can find the full list of these annotations in the [archetypes folder](annotations/src/main/java/com/smalaca/annotations/archetypes).
- **Architecture Annotations**: Used to document the architectural patterns applied in the project, such as [Ports and Adapters](annotations/src/main/java/com/smalaca/annotations/architecture/PortsAndAdaptersArchitecture.java).
- **DDD Annotations**: These annotations represent [Domain-Driven Design](annotations/src/main/java/com/smalaca/annotations/architecture/DomainDrivenDesign.java) building blocks like Aggregates, Entities, and Value Objects.

If you want to see the implementation of a specific archetype element listed below, you can simply navigate to the corresponding archetype annotation and check its usage in the IDE.

## Archetypes and Elements
The repository uses custom annotations to identify the archetypes and their constituent elements. Below is the list of archetypes implemented in this project:

### Party
Represents an individual or an organization involved in business processes.
- **Party**: The base archetype for individuals and organizations.
- **Party Identifier**: A unique identifier for a party.
- **Person**: An individual human being.
- **Organization**: A group of people or a legal entity.
- **Organization Name**: The formal name of an organization.
- **Registered Identifier**: An identifier provided by an official registry.
- **Organization Unit**: A sub-unit or department within an organization.
- **Address**: A physical or virtual location associated with a party.
- **Party Role**: The role a party plays in a specific context (e.g., Customer, Supplier).
- **Party Role Type**: The definition or category of a role.
- **Party Role Identifier**: A unique identifier for a specific role.
- **Party Role Constraint**: Rules or constraints applied to a role.
- **Party Relationship**: A connection between two parties.
- **Party Relationship Type**: The nature of the relationship (e.g., Employment, Ownership).
- **Party Relationship Constraint**: Rules governing a relationship.

#### Examples
- **Party**: [`Users Management Bounded Context: User`](training-center/src/main/java/com/smalaca/trainingcenter/usersmanagement/domain/user/User.java)
- **Person**: [`Users Management Bounded Context: User`](training-center/src/main/java/com/smalaca/trainingcenter/usersmanagement/domain/user/User.java)
- **Organization**: [`Companies Catalogue Bounded Context: Company`](training-center/src/main/java/com/smalaca/trainingcenter/companiescatalogue/domain/company/Company.java)
- **Party Role**: [`Accounting Bounded Context: Buyer`](training-center/src/main/java/com/smalaca/trainingcenter/accounting/domain/buyer/Buyer.java)
- **Party Relationship**: [`Companies Catalogue Bounded Context: Representation`](training-center/src/main/java/com/smalaca/trainingcenter/companiescatalogue/domain/representation/Representation.java)

### Product
Describes the types of products or services offered by the enterprise.
- **Product Type**: The definition of a category of products.
- **Product Type Identifier**: Unique identifier for a product type.
- **Product Instance**: A specific, individual occurrence of a product.
- **Product Instance Identifier**: Unique identifier for a specific product instance.
- **Package Type**: A definition of a bundle of products or services.
- **Package Instance**: A specific instance of a product package.
- **Product Feature Type**: A characteristic or feature that can be associated with a product type.
- **Product Feature Instance**: A specific occurrence of a feature on a product instance.
- **Price**: The cost associated with a product or service.

#### Examples
- **Product Type**: [`Training Portfolio Bounded Context: TrainingOffer`](training-center/src/main/java/com/smalaca/trainingcenter/trainingportfolio/domain/trainingoffer/TrainingOffer.java)
- **Product Instance**: [`Training Delivery Bounded Context: TrainingSession`](training-center/src/main/java/com/smalaca/trainingcenter/trainingdelivery/domain/trainingsession/TrainingSession.java)
- **Package Type**: [`Training Portfolio Bounded Context: LearningPath`](training-center/src/main/java/com/smalaca/trainingcenter/trainingportfolio/domain/learningpath/LearningPath.java)
- **Package Instance**: [`Training elivery Bounded Context: LearningPathEdition`](training-center/src/main/java/com/smalaca/trainingcenter/trainingdelivery/domain/learningpathedition/LearningPathEdition.java)

### Catalog
A collection of catalog entries that describe products or services available to the business.
- **Catalog**: The collection or container of available entries.
- **Catalog Entry**: An individual item within a catalog, providing details about a specific product or service type.

#### Examples
- **Catalog**: The full implementation of the Catalog is across multiple classes responsible for managing products and their availability:
  - [`Catalog Bounded Context: LearningOfferApplicationService`](training-center/src/main/java/com/smalaca/trainingcenter/catalog/application/learningoffer/LearningOfferApplicationService.java)
  - [`Catalog Bounded Context: LearningOfferRepository`](training-center/src/main/java/com/smalaca/trainingcenter/catalog/domain/learningoffer/LearningOfferRepository.java)
  - [`Catalog Bounded Context: LearningOfferReadModelRepository`](training-center/src/main/java/com/smalaca/trainingcenter/catalog/query/learningoffer/LearningOfferReadModelRepository.java)
- **Catalog Entry**: [`Catalog Bounded Context: LearningOffer`](training-center/src/main/java/com/smalaca/trainingcenter/catalog/domain/learningoffer/LearningOffer.java)

### Inventory
Manages the tracking of physical or virtual items that are in stock or available for use.
- **Inventory**: The system or container for tracking items.
- **Inventory Entry**: A record of a specific item or group of items within the inventory.

#### Examples
- **Inventory**: The full implementation of the Inventory is across multiple classes responsible for managing products and their availability:
  - [`Inventory Bounded Context: TrainingAvailabilityApplicationService`](training-center/src/main/java/com/smalaca/trainingcenter/inventory/application/trainingavailability/TrainingAvailabilityApplicationService.java)
  - [`Inventory Bounded Context: ReservationApplicationService`](training-center/src/main/java/com/smalaca/trainingcenter/inventory/application/reservation/ReservationApplicationService.java)
  - [`Inventory Bounded Context: TrainingAvailabilityRepository`](training-center/src/main/java/com/smalaca/trainingcenter/inventory/domain/traininginventory/TrainingAvailabilityRepository.java)
  - [`Inventory Bounded Context: TrainingAvailabilityReadModelRepository`](training-center/src/main/java/com/smalaca/trainingcenter/inventory/query/trainingavailability/TrainingAvailabilityReadModelRepository.java)
- **Inventory Entry**: [`Inventory Bounded Context: TrainingAvailability`](training-center/src/main/java/com/smalaca/trainingcenter/inventory/domain/traininginventory/TrainingAvailability.java)

### Availability
Represents the ability to provide or reserve a resource or service within a specific timeframe.
- **Availability**: Defines the period or conditions under which something is available.
- **Reservation Request**: A request to hold a specific resource.
- **Reservation**: A confirmed hold on a resource for a specific party.
- **Availability Policy**: Rules governing how availability is determined.
- **Cancellation Policy**: Rules governing the cancellation of reservations.

#### Examples
- **Availability**: [`Inventory Bounded Context: TrainingAvailability`](training-center/src/main/java/com/smalaca/trainingcenter/inventory/domain/traininginventory/TrainingAvailability.java)
- **Reservation**: [`Inventory Bounded Context: Reservation`](training-center/src/main/java/com/smalaca/trainingcenter/inventory/domain/reservation/Reservation.java)
- **Reservation Request**: [`Inventory Bounded Context: ReservationRequest`](training-center/src/main/java/com/smalaca/trainingcenter/inventory/domain/traininginventory/ReservationRequest.java)

### Order
Represents a formal agreement or request for the provision of products or services.
- **Order**: The overall agreement or transaction.
- **Sales Order**: A specific type of order representing a sale to a customer.
- **Order Line**: An individual item or service line within an order.
- **Party Summary**: A summary of the parties involved in the order.
- **Order Status**: Represents the current state of an order in its lifecycle.
- **Order Manager**: Responsible for managing the lifecycle and rules of orders.
- **Order Event**: An event that triggers a change in the order state.
- **Open Event**: Event indicating the opening of an order.
- **Cancel Event**: Event indicating the cancellation of an order.
- **Payment Event**: Event indicating a payment action related to the order.

#### Examples
- **Order**: [`Training Orders Bounded Context: TrainingOrder`](training-center/src/main/java/com/smalaca/trainingcenter/trainingorders/domain/trainingorder/TrainingOrder.java)
- **Order Manager**: The full implementation of the Order Manager is across multiple classes responsible for managing orders and their lifecycle:
  - [`Training Orders Bounded Context: TrainingOrderApplicationService`](training-center/src/main/java/com/smalaca/trainingcenter/trainingorders/application/trainingorder/TrainingOrderApplicationService.java)
  - [`Training Orders Bounded Context: TrainingOrderRepository`](training-center/src/main/java/com/smalaca/trainingcenter/trainingorders/domain/trainingorder/TrainingOrderRepository.java)

### Rule
Defines business rules and constraints that govern behavior and decision-making.
- **Rule Set**: A collection of related business rules.
- **Rule**: An individual business rule or constraint.
- **Rule Context**: The environment or state in which a rule is evaluated.
- **Rule Override**: A mechanism to bypass or change a rule's behavior in specific cases.
- **Party Signature**: A formal sign-off or approval required by a party.

#### Examples
- **Rule Set**: [`Inventory Bounded Context: TrainingEnrollmentPolicy`](training-center/src/main/java/com/smalaca/trainingcenter/inventory/domain/traininginventory/TrainingEnrollmentPolicy.java)
- **Rule Context**: [`Inventory Bounded Context: TrainingEnrollmentContext`](training-center/src/main/java/com/smalaca/trainingcenter/inventory/domain/traininginventory/TrainingEnrollmentContext.java)
- **Rule Override**: [`Inventory Bounded Context: EnrollmentApproval`](training-center/src/main/java/com/smalaca/trainingcenter/inventory/domain/reservation/EnrollmentApproval.java)

## Collaboration
I welcome contributions and feedback!
- **Issues**: Feel free to create issues for bugs, suggestions, or questions.
- **Fork**: Feel free to fork the repository to experiment with your own changes.
- **Pull Requests**: Feel free to submit pull requests with improvements or new archetype examples.

