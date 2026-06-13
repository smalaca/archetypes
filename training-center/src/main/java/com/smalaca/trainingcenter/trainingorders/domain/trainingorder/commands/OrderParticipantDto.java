package com.smalaca.trainingcenter.trainingorders.domain.trainingorder.commands;

import java.util.UUID;

public record OrderParticipantDto(UUID participantId, String displayName, String role) {
}
