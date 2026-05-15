package com.smalaca.archetypes.party.full;

import org.junit.jupiter.api.Test;
import static com.smalaca.archetypes.party.full.EnterpriseClientAssert.assertThat;

class EnterpriseClientTest {
    private final ClientManagementService service = new ClientManagementService();

    @Test
    void shouldManageEnterpriseClientWithBranchesAndAddresses() {
        EnterpriseClient client = new EnterpriseClient("Enterprise Corp", new VatNumber("VAT-123456789"), "Technology");
        
        service.addAddress(client, "Berlin", "Main Street 1");
        service.registerBranch(client, "Branch Berlin", new BranchCode("BR-BER-01"));
        service.registerBranch(client, "Branch Munich", new BranchCode("BR-MUN-01"));
        
        ClientBranch berlinBranch = client.getUnit("Branch Berlin");
        service.addAddress(berlinBranch, "Berlin", "Branch Street 1");
        
        ClientBranch munichBranch = client.getUnit("Branch Munich");
        munichBranch.addUnit(new ClientBranch("Munich Sales", new BranchCode("BR-MUN-SAL-01")));

        assertThat(client)
                .hasName("Enterprise Corp")
                .hasVatNumber("VAT-123456789")
                .hasIndustry("Technology")
                .hasAddressIn("Berlin", "Main Street 1")
                .hasBranches(2)
                .hasBranch("Branch Berlin", "BR-BER-01")
                .hasBranchAddressIn("Branch Berlin", "Berlin", "Branch Street 1")
                .hasBranch("Branch Munich", "BR-MUN-01")
                .hasSubBranch("Branch Munich", "Munich Sales", "BR-MUN-SAL-01");
    }

    @Test
    void shouldManageEnterpriseClientWithoutBranchAndWithoutAddress() {
        EnterpriseClient client = new EnterpriseClient("Simple Corp", new VatNumber("VAT-001"), "Services");

        assertThat(client)
                .hasName("Simple Corp")
                .hasVatNumber("VAT-001")
                .hasIndustry("Services")
                .hasNoBranches()
                .hasNoAddresses();
    }

    @Test
    void shouldManageEnterpriseClientWithoutBranchAndWithAddress() {
        EnterpriseClient client = new EnterpriseClient("Local Shop", new VatNumber("VAT-002"), "Retail");
        service.addAddress(client, "Krakow", "Main Market 1");

        assertThat(client)
                .hasName("Local Shop")
                .hasVatNumber("VAT-002")
                .hasIndustry("Retail")
                .hasNoBranches()
                .hasAddressIn("Krakow", "Main Market 1");
    }

    @Test
    void shouldManageEnterpriseClientWithAddressAndVariousBranches() {
        EnterpriseClient client = new EnterpriseClient("Global Tech", new VatNumber("VAT-003"), "IT");
        service.addAddress(client, "Warsaw", "Centrum 1");

        // branch with units (sub-branches)
        service.registerBranch(client, "Main Office", new BranchCode("BC-001"));
        ClientBranch mainOffice = client.getUnit("Main Office");
        mainOffice.addUnit(new ClientBranch("HR Unit", new BranchCode("BC-001-HR")));

        // branch with no units
        service.registerBranch(client, "Sales Office", new BranchCode("BC-002"));

        // branch with address
        service.registerBranch(client, "London Branch", new BranchCode("BC-003"));
        ClientBranch londonBranch = client.getUnit("London Branch");
        service.addAddress(londonBranch, "London", "Abbey Road 1");

        assertThat(client)
                .hasName("Global Tech")
                .hasVatNumber("VAT-003")
                .hasIndustry("IT")
                .hasAddressIn("Warsaw", "Centrum 1")
                .hasBranches(3)
                // branches with units
                .hasBranch("Main Office", "BC-001")
                .hasSubBranch("Main Office", "HR Unit", "BC-001-HR")
                // branches with no units
                .hasBranch("Sales Office", "BC-002")
                .hasNoSubBranches("Sales Office")
                // branches with address
                .hasBranch("London Branch", "BC-003")
                .hasBranchAddressIn("London Branch", "London", "Abbey Road 1")
                // branches with no address
                .hasNoBranchAddress("Main Office")
                .hasNoBranchAddress("Sales Office");
    }

    @Test
    void shouldManageEnterpriseClientWithMultipleAddresses() {
        EnterpriseClient client = new EnterpriseClient("MultiAddr Corp", new VatNumber("VAT-MULT"), "Logistics");
        service.addAddress(client, "Paris", "Rue de Rivoli 1");
        service.addAddress(client, "Lyon", "Place Bellecour 10");

        assertThat(client)
                .hasName("MultiAddr Corp")
                .hasVatNumber("VAT-MULT")
                .hasIndustry("Logistics")
                .hasAddresses(2)
                .hasAddressIn("Paris", "Rue de Rivoli 1")
                .hasAddressIn("Lyon", "Place Bellecour 10");
    }

    @Test
    void shouldManageEnterpriseClientWithTwoBranchesWhereOneHasMultipleAddressesAndOtherHasNoAddress() {
        EnterpriseClient client = new EnterpriseClient("BranchAddr Corp", new VatNumber("VAT-BR-ADDR"), "Transport");
        service.registerBranch(client, "No Address Branch", new BranchCode("BC-NONE"));
        service.registerBranch(client, "Multi Address Branch", new BranchCode("BC-MULTI"));

        ClientBranch multiBranch = client.getUnit("Multi Address Branch");
        service.addAddress(multiBranch, "Berlin", "Alexanderplatz 1");
        service.addAddress(multiBranch, "Hamburg", "Reeperbahn 69");

        assertThat(client)
                .hasName("BranchAddr Corp")
                .hasVatNumber("VAT-BR-ADDR")
                .hasIndustry("Transport")
                .hasBranches(2)
                .hasBranch("No Address Branch", "BC-NONE")
                .hasNoBranchAddress("No Address Branch")
                .hasBranch("Multi Address Branch", "BC-MULTI")
                .hasBranchAddresses("Multi Address Branch", 2)
                .hasBranchAddressIn("Multi Address Branch", "Berlin", "Alexanderplatz 1")
                .hasBranchAddressIn("Multi Address Branch", "Hamburg", "Reeperbahn 69");
    }
}
