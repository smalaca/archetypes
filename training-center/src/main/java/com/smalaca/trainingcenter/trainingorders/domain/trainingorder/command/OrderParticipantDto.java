package com.smalaca.trainingcenter.trainingorders.domain.trainingorder.command;

import java.util.UUID;

public record OrderParticipantDto(UUID participantId, String displayName, String role) {
}
