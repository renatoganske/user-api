package io.github.renatoganske.userapi.dtos;

import java.time.LocalDate;

public record UserDTO(

        Long userId,

        String name,

        String lastname,

        String email,

        String phone,

        LocalDate birthDate,

        Boolean status
) {
}
