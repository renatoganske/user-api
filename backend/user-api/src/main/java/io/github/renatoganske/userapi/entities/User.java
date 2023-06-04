package io.github.renatoganske.userapi.entities;

import io.github.renatoganske.userapi.dtos.CreateAndUpdateUserDTO;
import io.github.renatoganske.userapi.dtos.ListUserDTO;
import io.github.renatoganske.userapi.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 45)
    private String lastname;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 14)
    private String phone;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean status;

    public User(CreateAndUpdateUserDTO createAndUpdateUserDTO) {
        this.name = createAndUpdateUserDTO.name();
        this.lastname = createAndUpdateUserDTO.lastname();
        this.email = createAndUpdateUserDTO.email();
        this.phone = createAndUpdateUserDTO.phone();
        this.birthDate = createAndUpdateUserDTO.birthDate();
        this.password = createAndUpdateUserDTO.password();
        this.status = createAndUpdateUserDTO.status();
    }

    public void update(CreateAndUpdateUserDTO updateUserDTO) {
        this.name = updateUserDTO.name();
        this.lastname = updateUserDTO.lastname();
        this.email = updateUserDTO.email();
        this.phone = updateUserDTO.phone();
        this.birthDate = updateUserDTO.birthDate();
        this.password = updateUserDTO.password();
        this.status = updateUserDTO.status();
    }

    public UserDTO toUserDTO() {
        return new UserDTO(
                this.userId,
                this.name,
                this.lastname,
                this.email,
                this.phone,
                this.birthDate,
                this.status
        );
    }

    public ListUserDTO toListUserDTO() {
        return new ListUserDTO(this.userId,
                this.name,
                this.lastname,
                this.status);
    }
}