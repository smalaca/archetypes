package com.smalaca.archetypes.examples.organization;

import com.smalaca.archetypes.party.OrganizationUnit;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CorporateHierarchyTest {
    @Test
    void shouldModelCorporateHierarchy() {
        CorporateEntity corporation = new CorporateEntity("Smalaca Corp", "PL1234567890");
        Department hr = new Department("Human Resources", "HR-001");
        Department it = new Department("Information Technology", "IT-001");

        corporation.getOrganization().addUnit(hr.getOrganizationUnit());
        corporation.getOrganization().addUnit(it.getOrganizationUnit());

        assertThat(corporation.getOrganization().getName()).isEqualTo("Smalaca Corp");
        assertThat(corporation.getTaxIdentifier()).isEqualTo("PL1234567890");
        assertThat(corporation.getOrganization().getUnits())
                .extracting(OrganizationUnit::getName)
                .containsExactlyInAnyOrder("Human Resources", "Information Technology");
    }
}
