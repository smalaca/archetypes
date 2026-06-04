package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import com.smalaca.archetypes.annotations.ArchetypeRule;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ArchetypeParty.PartyRole
@ArchetypeParty.PartyRoleType
public class Mentor {
    private final MentorId mentorId;
    private final UserId userId;
    private final MentorNumber mentorNumber;
    @ArchetypeRule.RuleSet
    private final List<MentoringRule> rules;
    private final List<UUID> initiatedMentoring = new ArrayList<>();

    Mentor(MentorId mentorId, UserId userId, MentorNumber mentorNumber, List<MentoringRule> rules) {
        this.mentorId = mentorId;
        this.userId = userId;
        this.mentorNumber = mentorNumber;
        this.rules = rules;
    }

    public void initiateMentoring(MentoringContext context) {
        if (canInitiate(context)) {
            initiatedMentoring.add(context.menteeId());
        }
    }

    private boolean canInitiate(MentoringContext context) {
        return rules.stream().allMatch(rule -> rule.isSatisfiedBy(context));
    }
}
