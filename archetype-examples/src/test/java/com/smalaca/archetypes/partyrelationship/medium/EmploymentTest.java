package com.smalaca.archetypes.partyrelationship.medium;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.smalaca.archetypes.partyrelationship.medium.EmploymentAssert.assertThat;

class EmploymentTest {
    private static final UUID EMPLOYER_ID = UUID.randomUUID();
    private static final UUID EMPLOYEE_ID_1 = UUID.randomUUID();
    private static final UUID EMPLOYEE_ID_2 = UUID.randomUUID();

    @Test
    void shouldCreateEmployment() {
        Employment employment = new Employment(EMPLOYER_ID);
        employment.addEmployee(EMPLOYEE_ID_1, "Software Engineer");
        employment.addEmployee(EMPLOYEE_ID_2, "Product Manager");

        assertThat(employment)
                .hasEmployer(EMPLOYER_ID)
                .hasEmployeesCount(2)
                .hasEmployee(EMPLOYEE_ID_1, "Software Engineer")
                .hasEmployee(EMPLOYEE_ID_2, "Product Manager");
    }
}
