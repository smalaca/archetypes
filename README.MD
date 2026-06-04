to dos: 
- what each module is about
- explain every archetype
- explain the example and the fact it is using modularized monolith internally
- explain different identifier - technical and business, and why this is important - form of the article with link here
- highlight those are bounded contexts and archetypes does not have to sit in one context
- tell where part is
- tell where is the party role that can be used by both Organization and Person

## Part
Party Role:
- rule set as policies

Party Relationship:
- party relationship
- party relationship type
- party relationship constrain + rule set

Organization Name

Organization Unit

Examples in form of tests

## Product
Product Type and Price
Product Instance and Price
Product Catalog and Catalog Entry
Package Type and Package Instance
ProductFeatureType and ProductFeatureInstance

Examples in form of tests

## Inventory
Inventory + InventoryEntry
Examples in form of tests

## Availability
ReservationRequest and Reservation (id to the product instance as well)
availability policy
cancellation policy
Examples in form of tests

## Order
Order and OrderItem
PurchaseOrder
SalesOrder
OrderManager
OrderStatus
PartySummary
OrderEvents
Examples in form of tests

## Rule
Rule
RuleSet
RuleContext
RuleOverride
PartySignature
Examples in form of tests

## Integrate with Sonar 
increase the coverage
onboard to sonar