package com.smalaca.archetypes.partyrelationship.medium;

import org.junit.jupiter.api.Test;

import static com.smalaca.archetypes.partyrelationship.medium.EmploymentAssert.assertThat;

class EmploymentTest {
    @Test
    void shouldCreateEmployment() {
        Employment employment = new Employment("Acme Corp");
        employment.addEmployee("John Doe", "Software Engineer");
        employment.addEmployee("Jane Doe", "Product Manager");

        assertThat(employment)
                .hasRelationshipType("Employment")
                .hasEmployerRoleType("Employer")
                .hasEmployer("Acme Corp")
                .hasEmployeesCount(2)
                .hasEmployee("John Doe", "Software Engineer")
                .hasEmployee("Jane Doe", "Product Manager");
    }
}
