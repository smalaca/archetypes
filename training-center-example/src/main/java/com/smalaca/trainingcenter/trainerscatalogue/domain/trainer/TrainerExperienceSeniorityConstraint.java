package com.smalaca.trainingcenter.trainerscatalogue.domain.trainer;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.PartyRoleConstrain
class TrainerExperienceSeniorityConstraint {
    boolean isSatisfiedBy(UserId userId) {
        return true;
    }
}
