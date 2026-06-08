package com.smalaca.trainingcenter.catalog.domain.trainingcatalog;

import com.smalaca.annotations.architecture.DomainDrivenDesign;
import java.util.UUID;

@DomainDrivenDesign.ValueObject
record CatalogEntryId(UUID id) {
}
