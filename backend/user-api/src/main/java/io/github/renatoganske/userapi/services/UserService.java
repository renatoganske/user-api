package io.github.renatoganske.userapi.services;

import io.github.renatoganske.userapi.dtos.CreateAndUpdateUserDTO;
import io.github.renatoganske.userapi.entities.User;
import io.github.renatoganske.userapi.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UsersRepository repository;

    public UserService(UsersRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User createUser(CreateAndUpdateUserDTO createAndUpdateUserDTO) throws Exception {
        boolean userExists = repository.existsByEmail(createAndUpdateUserDTO.email());
        if (userExists) {
            throw new Exception("O email já está cadastrado");
        }
        return repository.save(new User(createAndUpdateUserDTO));
    }

    public List<User> listAllUsers() {
        List<User> users = repository.findAll();
        return users;
    }

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public User updateUser(Long id, CreateAndUpdateUserDTO updateUserDTO) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.update(updateUserDTO);
        return repository.save(user);
    }

    public void delete(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        repository.delete(user);
    }
}

