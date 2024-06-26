package io.github.renatoganske.userapi.controllers;

import io.github.renatoganske.userapi.dtos.CreateAndUpdateUserDTO;
import io.github.renatoganske.userapi.dtos.ListUserDTO;
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
    public ResponseEntity<List<ListUserDTO>> getAll() {
        List<ListUserDTO> users = service.listAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO dto = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id,
                                          @Valid @RequestBody CreateAndUpdateUserDTO updateUserDTO) {
        User user = service.updateUser(id, updateUserDTO);
        return ResponseEntity.status(HttpStatus.OK).body(user.toUserDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
