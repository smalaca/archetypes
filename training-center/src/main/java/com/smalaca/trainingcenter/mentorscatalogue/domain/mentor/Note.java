package com.smalaca.trainingcenter.mentorscatalogue.domain.mentor;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import java.time.LocalDateTime;
import java.util.UUID;

@DomainDrivenDesign.ValueObject
record Note(String content, LocalDateTime createdAt, UUID createdBy) {
}
