package io.github.renatoganske.userapi.services;

import io.github.renatoganske.userapi.dtos.CreateAndUpdateUserDTO;
import io.github.renatoganske.userapi.dtos.UserDTO;
import io.github.renatoganske.userapi.entities.User;
import io.github.renatoganske.userapi.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UsersRepository repository;

    public UserService(UsersRepository repository) {
        this.repository = repository;
    }

    public User createUser(CreateAndUpdateUserDTO createAndUpdateUserDTO) throws Exception {
        boolean userExists = repository.existsByEmail(createAndUpdateUserDTO.email());
        if (userExists) {
            throw new Exception("O email já está cadastrado");
        }
        return repository.save(new User(createAndUpdateUserDTO));
    }

    public List<User> listAllUsers(){
        List<User> users = repository.findAll();
        return users;
    }

}
