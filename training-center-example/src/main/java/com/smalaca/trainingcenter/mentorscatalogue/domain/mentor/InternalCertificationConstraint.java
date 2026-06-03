package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleConstraint
class InternalCertificationConstraint implements MentorConstraint {
    private final CertificationService certificationService;

    InternalCertificationConstraint(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @Override
    public boolean isSatisfiedBy(UserId userId) {
        return certificationService.isCertified(userId);
    }
}
