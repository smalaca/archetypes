package com.smalaca.archetypes.partyrelationship.medium;

import org.junit.jupiter.api.Test;
import static com.smalaca.archetypes.partyrelationship.medium.EmploymentAssert.assertThat;

class EmploymentTest {
    @Test
    void shouldCreateEmploymentWithMultipleEmployees() {
        Employment employment = new Employment("Smalaca Corp");
        employment.addEmployee("John Doe", "Software Engineer");
        employment.addEmployee("Jane Doe", "Project Manager");

        assertThat(employment)
                .hasEmployer("Smalaca Corp")
                .hasEmployeesCount(2)
                .hasEmployee("John Doe", "Software Engineer")
                .hasEmployee("Jane Doe", "Project Manager");
    }
}
