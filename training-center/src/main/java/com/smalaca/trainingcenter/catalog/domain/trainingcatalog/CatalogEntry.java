package com.smalaca.trainingcenter.catalog.domain.trainingcatalog;

import com.smalaca.annotations.archetypes.ArchetypeProduct;
import com.smalaca.annotations.architecture.DomainDrivenDesign;

import java.util.ArrayList;
import java.util.List;

@DomainDrivenDesign.Entity
@ArchetypeProduct.CatalogEntry
public class CatalogEntry {
    private final CatalogEntryId catalogEntryId;
    private final String title;
    private final String description;
    private final List<TrainingOfferId> trainingOfferIds = new ArrayList<>();

    public CatalogEntry(CatalogEntryId catalogEntryId, String title, String description) {
        this.catalogEntryId = catalogEntryId;
        this.title = title;
        this.description = description;
    }

    public void add(TrainingOfferId trainingOfferId) {
        trainingOfferIds.add(trainingOfferId);
    }
}
