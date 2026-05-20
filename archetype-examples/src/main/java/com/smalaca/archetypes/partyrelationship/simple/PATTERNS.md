# When to Skip Dedicated Relationship and Role Classes

This document describes three examples that demonstrate when it makes sense to use the **PartyRelationship Archetype Pattern** in its most simplified form — without dedicated, extensible `Party`, `RelationshipType`, or `RoleType` classes.

## The Core Idea

The full PartyRelationship Archetype Pattern introduces dedicated classes for every concept:
- A `Party` hierarchy (Person, Organization)
- A `RelationshipType` value object
- A `PartyRole` class linking a `Party` to a `RoleType`
- A `RoleType` value object

This is powerful when you need **extensibility**, **polymorphism**, or **runtime flexibility**. However, in many real-world scenarios, the domain is narrow and well-understood. The relationship type is fixed, the roles are obvious from the class name, and no extension is anticipated.

In such cases, introducing all those classes adds **accidental complexity** without delivering any benefit. The simplified pattern is justified when:

1. **The class name is self-descriptive** — `Friendship`, `ColleagueRelationship`, `BoardMembership` already communicate the relationship type.
2. **Roles are obvious and fixed** — a `Friendship` always has two friends; a `ColleagueRelationship` always has colleagues.
3. **No extension is needed** — the domain will not introduce new role types or relationship types dynamically.
4. **Parties are simple** — parties can be represented as plain Strings or simple value objects without a full `Party` hierarchy.

---

## Example 1: Person–Person Relationship (Friendship)

### Description

Two people who know each other socially. The relationship is **symmetric** — both sides play the same role.

### Why the Simplified Pattern Makes Sense

- The relationship type is captured by the class name `Friendship` — no separate `RelationshipType` class is needed.
- Both participants play the role of "Friend" — a single `FriendRole` class is sufficient for both sides.
- A person in this context is just a name — no `Party` hierarchy is needed.
- There are no business rules to enforce — no `RelationshipConstraint` is needed.
- There is no need to identify parties uniquely — no `PartyIdentifier` is needed.

### Structure

```
Friendship                  ← @PartyRelationship (class name = relationship type)
  relationshipType: String  ← @RelationshipType  (fixed value "Friendship")
  friend1: FriendRole       ← @PartyRole
  friend2: FriendRole       ← @PartyRole

FriendRole                  ← @PartyRole
  roleType: String          ← @RoleType (fixed value "Friend")
  person: String            ← @Party
```

### Justification

The `Friendship` class is completely self-descriptive. Adding a `RelationshipType` class, a `Party` abstract class, and a `RoleType` class would only add boilerplate. The pattern is still fully honored — all mandatory archetype parts are present, just realized as simple fields and strings.

---

## Example 2: Organization–Person Relationship (Employment)

### Description

A company employs one or more people. The relationship is **asymmetric** — the two sides play different roles (Employer vs. Employee), and the cardinality is one-to-many.

### Why the Simplified Pattern Makes Sense

- The relationship type is captured by the class name `Employment`.
- The two roles are distinct and fixed: `EmployerRole` (the company) and `EmployeeRole` (the person). No new role types will be introduced.
- Both the employer (organization name) and employees (name + position) are simple enough to be represented as strings — no `Party` hierarchy is needed.
- The one-to-many cardinality (one employer, many employees) is naturally expressed as a `List<EmployeeRole>`.
- No constraint validation is required.

### Structure

```
Employment                        ← @PartyRelationship
  relationshipType: String        ← @RelationshipType (fixed value "Employment")
  employer: EmployerRole          ← @PartyRole
  employees: List<EmployeeRole>   ← @PartyRole

EmployerRole                      ← @PartyRole
  roleType: String                ← @RoleType (fixed value "Employer")
  name: String                    ← @Party

EmployeeRole                      ← @PartyRole
  roleType: String                ← @RoleType (fixed value "Employee")
  name: String                    ← @Party
  position: String
```

### Justification

The asymmetry between employer and employee is naturally expressed by having two distinct role classes. The class names make the roles immediately obvious. Introducing a generic `Party` class or a `RoleType` value object would not add clarity — it would obscure it. The pattern is still fully honored.

---

## Example 3: Organization–Person Together (Board Membership)

### Description

A person holds a seat on the board of an organization. This is a **named, positional relationship** between a person and an organization — neither symmetric nor a simple employment. The person has a specific title (e.g., "Chairperson", "Treasurer"), and the organization is identified by name.

### Why the Simplified Pattern Makes Sense

- The relationship type is captured by the class name `BoardMembership`.
- The two roles are distinct and fixed: `BoardOrganizationRole` (the organization hosting the board) and `BoardMemberRole` (the person with a title).
- The organization is just a name; the person is a name with a title — no `Party` hierarchy is needed.
- The title is a role-specific attribute on `BoardMemberRole`, not a generic `RoleType` — it belongs to the role class itself.
- No constraint validation is required.

### Structure

```
BoardMembership                          ← @PartyRelationship
  relationshipType: String               ← @RelationshipType (fixed value "BoardMembership")
  organization: BoardOrganizationRole    ← @PartyRole
  member: BoardMemberRole                ← @PartyRole

BoardOrganizationRole                    ← @PartyRole
  roleType: String                       ← @RoleType (fixed value "BoardOrganization")
  organizationName: String               ← @Party

BoardMemberRole                          ← @PartyRole
  roleType: String                       ← @RoleType (fixed value "BoardMember")
  personName: String                     ← @Party
  title: String
```

### Justification

This example shows that even when a person and an organization are involved together, the simplified pattern is valid as long as the roles are fixed and the domain is narrow. The `title` field on `BoardMemberRole` is a role-specific attribute — it describes how the person participates, not who they are. This is a natural fit for the simplified pattern: the class structure is self-descriptive, and no extension point is needed.

---

## Summary: When to Use the Simplified Pattern

| Criterion                          | Simplified Pattern | Full Pattern |
|------------------------------------|--------------------|--------------|
| Relationship type is fixed         | ✅ Use class name   | ✅ Use `RelationshipType` class |
| Roles are fixed and obvious        | ✅ Use named fields | ✅ Use `RoleType` class |
| Parties are simple (name/string)   | ✅ Use String       | ✅ Use `Party` hierarchy |
| Extension of roles is anticipated  | ❌ Not suitable     | ✅ Designed for this |
| Runtime polymorphism needed        | ❌ Not suitable     | ✅ Designed for this |
| Constraint validation required     | ❌ Not suitable     | ✅ Use `RelationshipConstraint` |
| Unique party identification needed | ❌ Not suitable     | ✅ Use `PartyIdentifier` |

The simplified pattern is not a shortcut — it is a **deliberate design choice** for domains where the relationship structure is well-understood, fixed, and self-descriptive. All mandatory archetype parts are still present; they are just realized in the simplest possible way.
