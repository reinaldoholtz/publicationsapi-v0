package io.github.publications.publicationsapi.application.users;

import io.github.publications.publicationsapi.application.jwt.JwtService;
import io.github.publications.publicationsapi.domain.AccessToken;
import io.github.publications.publicationsapi.domain.entity.Publication;
import io.github.publications.publicationsapi.domain.entity.User;
import io.github.publications.publicationsapi.domain.exception.DuplicateTupleException;
import io.github.publications.publicationsapi.domain.service.UserService;
import io.github.publications.publicationsapi.infra.repostiory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getById(String id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        var possibleUser = getByEmail(user.getEmail());
        if (possibleUser != null){
            throw new DuplicateTupleException("User already exists!");
        }
        encodePassword(user);
        return userRepository.save(user);
    }

    @Override
    public AccessToken authenticate(String email, String password) {
        var user =getByEmail(email);
        if(user == null){
            return null;
        }
        boolean matches = passwordEncoder.matches(password, user.getPassword());

        if (matches){
            return jwtService.generateToken(user);
        }
        return null;
    }

    private void encodePassword(User user){
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
    }
}
