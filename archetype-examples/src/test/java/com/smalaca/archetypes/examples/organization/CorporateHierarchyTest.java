package com.smalaca.archetypes.examples.organization;

import org.junit.jupiter.api.Test;
import static com.smalaca.archetypes.examples.organization.CorporateEntityAssert.assertThat;

class CorporateHierarchyTest {
    @Test
    void shouldModelCorporateHierarchy() {
        CorporateEntity corporation = new CorporateEntity("Smalaca Corp", "PL1234567890");
        Department hr = new Department("Human Resources", "HR-001");
        Department it = new Department("Information Technology", "IT-001");
        Department dev = new Department("Software Development", "IT-DEV-001");

        it.addSubDepartment(dev);
        corporation.addDepartment(hr);
        corporation.addDepartment(it);

        assertThat(corporation)
                .hasName("Smalaca Corp")
                .hasTaxIdentifier("PL1234567890")
                .hasDepartments("Human Resources", "Information Technology")
                .hasSubDepartment("Information Technology", "Software Development");
    }
}
