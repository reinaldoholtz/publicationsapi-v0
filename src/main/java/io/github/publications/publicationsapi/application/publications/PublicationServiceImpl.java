package io.github.publications.publicationsapi.application.publications;

import io.github.publications.publicationsapi.domain.entity.Publication;
import io.github.publications.publicationsapi.domain.service.PublicationService;
import io.github.publications.publicationsapi.infra.repostiory.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository repository;

    @Override
    @Transactional
    public Publication save(Publication publication) {
        return repository.save(publication);
    }

    @Override
    public Optional<Publication> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Publication> getByDocument(String query) {
        return repository.findByDocumentTsQuery(query);
    }

    @Override
    public List<Publication> getByDescription(String description) {
        return repository.findByDescriptionLike(description);
    }

    @Override
    public List<Publication> getAllPublications() {
        return repository.findAll();
    }
}
