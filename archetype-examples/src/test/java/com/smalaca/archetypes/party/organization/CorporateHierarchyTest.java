package com.smalaca.archetypes.party.organization;

import org.junit.jupiter.api.Test;
import static com.smalaca.archetypes.party.organization.CorporateEntityAssert.assertThat;
import static com.smalaca.archetypes.party.organization.DepartmentAssert.assertThat;

class CorporateHierarchyTest {
    @Test
    void shouldModelCorporateHierarchy() {
        CorporateEntity corporation = new CorporateEntity("Smalaca Corp", new TaxIdentifier("PL1234567890"));
        Department hr = new Department("Human Resources", new DepartmentCode("HR-001"));
        Department it = new Department("Information Technology", new DepartmentCode("IT-001"));
        Department dev = new Department("Software Development", new DepartmentCode("IT-DEV-001"));

        it.add(dev);
        corporation.add(hr);
        corporation.add(it);

        assertThat(corporation)
                .hasName("Smalaca Corp")
                .hasTaxIdentifier("PL1234567890")
                .hasDepartments(2)
                .hasDepartment("Human Resources", "HR-001")
                .hasDepartment("Information Technology", "IT-001");

        assertThat(corporation.getDepartment("Information Technology"))
                .hasDepartments(1)
                .hasDepartment("Software Development", "IT-DEV-001");
    }
}
