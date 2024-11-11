package io.github.publications.publicationsapi.application.users;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private String id;
    private String name;
    private String email;
}
