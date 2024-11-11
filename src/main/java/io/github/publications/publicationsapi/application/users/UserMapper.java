package io.github.publications.publicationsapi.application.users;

import io.github.publications.publicationsapi.domain.AccessToken;
import io.github.publications.publicationsapi.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToUser(UserDTO dto){
        return User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
                .build();
    }

    public UserDTO userToDTO(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public UserResponseDTO userToResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public UserTokenResponseDTO userTokenToDTO(User user, AccessToken token ) {
        var userResponseDto = this.userToResponseDTO(user);
        return UserTokenResponseDTO.builder()
                .user(userResponseDto)
                .accessToken(token.getAccessToken())
                .build();
    }
}
