package com.smalaca.trainingcenter.companiescatalogue.domain.representative;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.AggregateRoot
@ArchetypeParty.PartyRole
public class Representative {
    private final RepresentativeId representativeId;
    private final UserId userId;
    private final String name;

    public Representative(RepresentativeId representativeId, UserId userId, String name) {
        this.representativeId = representativeId;
        this.userId = userId;
        this.name = name;
    }
}
