package io.github.publications.publicationsapi.domain.service;

import io.github.publications.publicationsapi.domain.AccessToken;
import io.github.publications.publicationsapi.domain.entity.Publication;
import io.github.publications.publicationsapi.domain.entity.User;

import java.util.Optional;

public interface UserService {
    User getByEmail(String email);

    User save(User user);

    AccessToken authenticate(String email, String password);

    Optional<User> getById(String id);
}
