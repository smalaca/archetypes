package com.smalaca.archetypes.partyrelationship.full;

import org.assertj.core.api.AbstractAssert;

public class OrganizationalRelationshipAssert extends AbstractAssert<OrganizationalRelationshipAssert, OrganizationalRelationship> {
    private OrganizationalRelationshipAssert(OrganizationalRelationship actual) {
        super(actual, OrganizationalRelationshipAssert.class);
    }

    public static OrganizationalRelationshipAssert assertThat(OrganizationalRelationship actual) {
        return new OrganizationalRelationshipAssert(actual);
    }

    public OrganizationalRelationshipAssert hasType(String typeName) {
        isNotNull();

        if (!actual.getRelationshipType().name().equals(typeName)) {
            failWithMessage("Expected relationship type to be <%s> but was <%s>", typeName, actual.getRelationshipType().name());
        }

        return this;
    }

    public OrganizationalRelationshipAssert hasRolesCount(int count) {
        isNotNull();

        if (actual.getRoles().size() != count) {
            failWithMessage("Expected roles count to be <%d> but was <%d>", count, actual.getRoles().size());
        }

        return this;
    }

    public OrganizationalRelationshipAssert hasRole(String roleName, String partyName) {
        isNotNull();

        boolean found = actual.getRoles().stream()
                .anyMatch(role -> {
                    boolean roleMatch = role.getRoleType().name().equals(roleName);
                    boolean partyMatch = false;
                    if (role.getParty() instanceof Person person) {
                        partyMatch = (person.getFirstName() + " " + person.getLastName()).equals(partyName);
                    } else if (role.getParty() instanceof Organization organization) {
                        partyMatch = organization.getName().equals(partyName);
                    }
                    return roleMatch && partyMatch;
                });

        if (!found) {
            failWithMessage("Expected role <%s> for party <%s> to be present", roleName, partyName);
        }

        return this;
    }

    public OrganizationalRelationshipAssert satisfies(RelationshipConstraint constraint) {
        isNotNull();

        if (!constraint.isSatisfiedBy(actual)) {
            failWithMessage("Expected relationship to satisfy the constraint but it did not");
        }

        return this;
    }

    public OrganizationalRelationshipAssert hasPartyIdentifier(String roleName, String identifier, String type) {
        isNotNull();
        boolean found = actual.getRoles().stream()
                .filter(role -> role.getRoleType().name().equals(roleName))
                .flatMap(role -> role.getParty().getIdentifiers().stream())
                .anyMatch(id -> id.identifier().equals(identifier) && id.type().equals(type));
        if (!found) {
            failWithMessage("Expected party in role <%s> to have identifier <%s> of type <%s>", roleName, identifier, type);
        }
        return this;
    }

    public OrganizationalRelationshipAssert doesNotSatisfy(RelationshipConstraint constraint) {
        isNotNull();

        if (constraint.isSatisfiedBy(actual)) {
            failWithMessage("Expected relationship NOT to satisfy the constraint but it did");
        }

        return this;
    }
}
