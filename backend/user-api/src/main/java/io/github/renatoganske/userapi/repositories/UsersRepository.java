package io.github.renatoganske.userapi.repositories;

import io.github.renatoganske.userapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

}
