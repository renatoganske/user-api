package io.github.renatoganske.userapi.dtos;

import java.time.LocalDate;

public record ListUserDTO(

        Long userId,

        String name,

        String lastname,

        Boolean status
) {
}
