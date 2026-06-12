package com.smalaca.trainingcenter.inventory.domain.reservation;

import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.Entity
@ArchetypeRule.RuleOverride
class EnrollmentApproval {
    private final ApprovalSignature approvalSignature;
    private final String justification;

    EnrollmentApproval(ApprovalSignature approvalSignature, String justification) {
        this.approvalSignature = approvalSignature;
        this.justification = justification;
    }
}
