package com.smalaca.archetypes.examples.organization;

import com.smalaca.archetypes.party.OrganizationUnit;
import org.junit.jupiter.api.Test;
import static com.smalaca.archetypes.examples.organization.CorporateEntityAssert.assertThat;

class CorporateHierarchyTest {
    @Test
    void shouldModelCorporateHierarchy() {
        CorporateEntity corporation = new CorporateEntity("Smalaca Corp", "PL1234567890");
        Department hr = new Department("Human Resources", "HR-001");
        Department it = new Department("Information Technology", "IT-001");

        corporation.getOrganization().addUnit(hr.getOrganizationUnit());
        corporation.getOrganization().addUnit(it.getOrganizationUnit());

        assertThat(corporation)
                .hasName("Smalaca Corp")
                .hasTaxIdentifier("PL1234567890")
                .hasDepartments("Human Resources", "Information Technology");
    }
}
