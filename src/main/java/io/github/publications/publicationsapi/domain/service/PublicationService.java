package io.github.publications.publicationsapi.domain.service;

import io.github.publications.publicationsapi.domain.entity.Publication;
import java.util.List;
import java.util.Optional;

public interface PublicationService {

    Publication save(Publication publication);

    Optional<Publication> getById(String id);

    List<Publication> getByDocument(String query);

    List<Publication> getByDescription(String description);

    List<Publication> getAllPublications();

}
