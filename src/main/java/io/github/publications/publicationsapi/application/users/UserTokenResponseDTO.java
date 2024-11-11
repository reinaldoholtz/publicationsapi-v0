package io.github.publications.publicationsapi.application.users;

import io.github.publications.publicationsapi.domain.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserTokenResponseDTO {
    private String accessToken;
    private UserResponseDTO user;
}
