package io.github.renatoganske.userapi.controllers;

import io.github.renatoganske.userapi.dtos.CreateAndUpdateUserDTO;
import io.github.renatoganske.userapi.dtos.UserDTO;
import io.github.renatoganske.userapi.entities.User;
import io.github.renatoganske.userapi.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@ControllerAdvice
public class UsersController {

    final
    UserService service;

    public UsersController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody CreateAndUpdateUserDTO createAndUpdateUserDTO) throws Exception {
        User newUser = service.createUser(createAndUpdateUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser.toUserDTO());
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> users = service.listAllUsers().stream().map(User::toUserDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        User user = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user.toUserDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                              @Valid @RequestBody CreateAndUpdateUserDTO updateUserDTO) {
        User user = service.updateUser(id, updateUserDTO);
        return ResponseEntity.status(HttpStatus.OK).body(user.toUserDTO());
    }
}
