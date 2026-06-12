package com.smalaca.trainingcenter.trainingorders.application.trainingorder;

import java.util.UUID;

public record OrderParticipantDto(UUID participantId, String displayName, String role) {
}
