package com.smalaca.archetypes.partyrelationship.full;

import org.junit.jupiter.api.Test;
import static com.smalaca.archetypes.partyrelationship.full.OrganizationalRelationshipAssert.assertThat;

class OrganizationalRelationshipTest {
    @Test
    void shouldCreateOrganizationalRelationshipAndSatisfyConstraints() {
        RelationshipType employmentType = new RelationshipType("Employment");
        OrganizationalRelationship relationship = new OrganizationalRelationship(employmentType);

        RoleType employerRole = new RoleType("Employer");
        Organization company = new Organization("Smalaca Corp");
        relationship.addRole(new PartyRole(company, employerRole));

        RoleType employeeRole = new RoleType("Employee");
        Person person = new Person("John", "Doe");
        relationship.addRole(new PartyRole(person, employeeRole));

        RelationshipConstraint constraint = new OrganizationalHierarchyConstraint("Employer", "Employee");

        assertThat(relationship)
                .hasType("Employment")
                .hasRolesCount(2)
                .hasRole("Employer", "Smalaca Corp")
                .hasRole("Employee", "John Doe")
                .satisfies(constraint);
    }

    @Test
    void shouldNotSatisfyConstraintWhenMissingRequiredRole() {
        RelationshipType employmentType = new RelationshipType("Employment");
        OrganizationalRelationship relationship = new OrganizationalRelationship(employmentType);

        RoleType employeeRole = new RoleType("Employee");
        Person person = new Person("John", "Doe");
        relationship.addRole(new PartyRole(person, employeeRole));

        RelationshipConstraint constraint = new OrganizationalHierarchyConstraint("Employer", "Employee");

        assertThat(relationship)
                .hasRolesCount(1)
                .doesNotSatisfy(constraint);
    }
}
