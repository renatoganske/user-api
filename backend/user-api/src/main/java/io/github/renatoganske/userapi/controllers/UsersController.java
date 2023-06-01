package io.github.renatoganske.userapi.controllers;

import io.github.renatoganske.userapi.dtos.CreateAndUpdateUserDTO;
import io.github.renatoganske.userapi.dtos.UserDTO;
import io.github.renatoganske.userapi.entities.User;
import io.github.renatoganske.userapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
