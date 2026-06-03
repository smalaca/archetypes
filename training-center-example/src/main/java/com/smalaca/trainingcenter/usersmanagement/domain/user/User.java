package com.smalaca.trainingcenter.usersmanagement.domain.user;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.Person
public class User {
    private final UserId userId;
    private final Login login;

    User(UserId userId, Login login) {
        this.userId = userId;
        this.login = login;
    }
}
