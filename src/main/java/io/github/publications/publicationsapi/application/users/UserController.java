package io.github.publications.publicationsapi.application.users;

import io.github.publications.publicationsapi.application.publications.PublicationDTO;
import io.github.publications.publicationsapi.domain.exception.DuplicateTupleException;
import io.github.publications.publicationsapi.domain.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity save(@RequestBody UserDTO dto){
        try{
            var user = userMapper.mapToUser(dto);
            userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (DuplicateTupleException e){
            Map<String, String> jsonResult = Map.of("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(jsonResult);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity authenticate(@RequestBody CredentialsDTO credentials) {
        var token = userService.authenticate(credentials.getEmail(), credentials.getPassword());
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + token.getAccessToken())
                .header("access-control-expose-headers", "Authorization")
                .build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id){
        var possibleUser = userService.getById(id);
        if (possibleUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var user = possibleUser.get();
        return  ResponseEntity.ok(userMapper.userToDTO(user));
    }
}
