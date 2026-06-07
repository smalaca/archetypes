package com.smalaca.trainingcenter.trainingdelivery.domain.trainingsession;

import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.time.LocalDate;

@DomainDrivenDesign.ValueObject
record TrainingDateRange(LocalDate startDate, LocalDate endDate) {
}
