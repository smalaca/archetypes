package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.archetypes.ArchetypeParty;
import com.smalaca.annotations.archetypes.ArchetypeRule;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DomainDrivenDesign.AggregateRoot
@ArchetypeParty.PartyRole
@ArchetypeParty.PartyRoleType
public class Mentor {
    private final MentorId mentorId;
    private final UserId userId;
    private final MentorNumber mentorNumber;
    @ArchetypeRule.RuleSet
    private final List<MentoringCondition> rules;
    private final List<Mentoring> mentorings = new ArrayList<>();

    Mentor(MentorId mentorId, UserId userId, MentorNumber mentorNumber, List<MentoringCondition> rules) {
        this.mentorId = mentorId;
        this.userId = userId;
        this.mentorNumber = mentorNumber;
        this.rules = rules;
    }

    public void initiateMentoring(MentoringContext context) {
        if (canInitiate(context)) {
            List<MenteeId> mentees = context.menteeIds().stream()
                    .map(MenteeId::new)
                    .collect(Collectors.toList());
            Mentoring mentoring = Mentoring.toBeStarted(mentorId, mentees);
            mentorings.add(mentoring);
        }
    }

    private boolean canInitiate(MentoringContext context) {
        return rules.stream().allMatch(rule -> rule.isSatisfiedBy(context));
    }
}
