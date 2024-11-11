package io.github.publications.publicationsapi.infra.repostiory;

import io.github.publications.publicationsapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    User getById(String id);

}
