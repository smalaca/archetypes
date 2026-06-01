package usersmanagement.model;

import com.smalaca.archetypes.annotations.ArchetypeParty;

@ArchetypeParty.Person
public class User {
    private final UserId userId;
    private final Login login;

    public User(UserId userId, Login login) {
        this.userId = userId;
        this.login = login;
    }
}
