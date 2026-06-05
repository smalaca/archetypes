package com.smalaca.trainingcenter.addressbook.domain.addressee;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

@DomainDrivenDesign.ValueObject
@ArchetypeParty.Address
record EmailAddress() implements Address {
}
