package com.smalaca.archetypes.examples.full;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class EnterpriseClientAssert extends AbstractAssert<EnterpriseClientAssert, EnterpriseClient> {
    private EnterpriseClientAssert(EnterpriseClient actual) {
        super(actual, EnterpriseClientAssert.class);
    }

    public static EnterpriseClientAssert assertThat(EnterpriseClient actual) {
        return new EnterpriseClientAssert(actual);
    }

    public EnterpriseClientAssert hasName(String name) {
        isNotNull();

        if (!actual.getName().equals(name)) {
            failWithMessage("Expected client name to be <%s> but was <%s>", name, actual.getName());
        }

        return this;
    }

    public EnterpriseClientAssert hasIndustry(String industry) {
        isNotNull();

        if (!actual.getIndustry().equals(industry)) {
            failWithMessage("Expected industry to be <%s> but was <%s>", industry, actual.getIndustry());
        }

        return this;
    }

    public EnterpriseClientAssert hasVatNumber(String vatNumber) {
        isNotNull();

        if (!actual.getVatNumber().value().equals(vatNumber)) {
            failWithMessage("Expected VAT number to be <%s> but was <%s>", vatNumber, actual.getVatNumber().value());
        }

        return this;
    }

    public EnterpriseClientAssert hasAddressIn(String city, String street) {
        isNotNull();

        Address address = actual.getAddresses().stream()
                .filter(a -> a.city().equals(city) && a.street().equals(street))
                .findFirst()
                .orElse(null);

        if (address == null) {
            failWithMessage("Expected client to have address in <%s> at <%s> but was not found", city, street);
        }

        return this;
    }

    public EnterpriseClientAssert hasNoAddresses() {
        isNotNull();

        if (!actual.getAddresses().isEmpty()) {
            failWithMessage("Expected client to have no addresses but had <%s>", actual.getAddresses().size());
        }

        return this;
    }

    public EnterpriseClientAssert hasBranchAddressIn(String branchName, String city, String street) {
        isNotNull();

        ClientBranch branch = findBranchByName(branchName);

        if (branch == null) {
            failWithMessage("Expected client to have branch <%s> but was not found", branchName);
        }

        Address address = branch.getAddresses().stream()
                .filter(a -> a.city().equals(city) && a.street().equals(street))
                .findFirst()
                .orElse(null);

        if (address == null) {
            failWithMessage("Expected branch <%s> to have address in <%s> at <%s> but was not found", branchName, city, street);
        }

        return this;
    }

    public EnterpriseClientAssert hasNoBranchAddress(String branchName) {
        isNotNull();

        ClientBranch branch = findBranchByName(branchName);

        if (branch == null) {
            failWithMessage("Expected client to have branch <%s> but was not found", branchName);
        }

        if (!branch.getAddresses().isEmpty()) {
            failWithMessage("Expected branch <%s> to have no addresses but had <%s>", branchName, branch.getAddresses().size());
        }

        return this;
    }

    public EnterpriseClientAssert hasBranch(String name, String code) {
        isNotNull();

        ClientBranch branch = findBranchByName(name);

        if (branch == null) {
            failWithMessage("Expected client to have branch <%s> but was not found", name);
        }

        if (!branch.getBranchCode().value().equals(code)) {
            failWithMessage("Expected branch <%s> to have code <%s> but was <%s>", name, code, branch.getBranchCode().value());
        }

        return this;
    }

    private ClientBranch findBranchByName(String name) {
        return actual.getUnits().stream()
                .filter(unit -> unit.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public EnterpriseClientAssert hasNoBranches() {
        isNotNull();

        if (!actual.getUnits().isEmpty()) {
            failWithMessage("Expected client to have no branches but had <%s>", actual.getUnits().size());
        }

        return this;
    }

    public EnterpriseClientAssert hasBranches(int count) {
        isNotNull();

        if (actual.getUnits().size() != count) {
            failWithMessage("Expected client to have <%s> branches but had <%s>", count, actual.getUnits().size());
        }

        return this;
    }

    public EnterpriseClientAssert hasSubBranch(String branchName, String subBranchName, String subBranchCode) {
        isNotNull();

        ClientBranch branch = findBranchByName(branchName);

        if (branch == null) {
            failWithMessage("Expected client to have branch <%s> but was not found", branchName);
        }

        ClientBranch subBranch = branch.getUnits().stream()
                .filter(unit -> unit.getName().equals(subBranchName))
                .findFirst()
                .orElse(null);

        if (subBranch == null) {
            failWithMessage("Expected branch <%s> to have sub-branch <%s> but was not found", branchName, subBranchName);
        }

        if (!subBranch.getBranchCode().value().equals(subBranchCode)) {
            failWithMessage("Expected sub-branch <%s> to have code <%s> but was <%s>", subBranchName, subBranchCode, subBranch.getBranchCode().value());
        }

        return this;
    }

    public EnterpriseClientAssert hasNoSubBranches(String branchName) {
        isNotNull();

        ClientBranch branch = findBranchByName(branchName);

        if (branch == null) {
            failWithMessage("Expected client to have branch <%s> but was not found", branchName);
        }

        if (!branch.getUnits().isEmpty()) {
            failWithMessage("Expected branch <%s> to have no sub-branches but had <%s>", branchName, branch.getUnits().size());
        }

        return this;
    }
}
