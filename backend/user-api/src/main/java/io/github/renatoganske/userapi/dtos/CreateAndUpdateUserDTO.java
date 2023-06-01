package io.github.renatoganske.userapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CreateAndUpdateUserDTO(

        @NotEmpty(message = "required.name.validation")
        @Length(min = 3, max = 45, message = "user.length.validation")
        String name,

        @NotEmpty(message = "required.lastname.validation")
        @Length(min = 3, max = 45, message = "user.length.validation")
        String lastname,

        @NotEmpty(message = "required.email.validation")
        @Length(min = 3, max = 45, message = "{user.length.validation}")
        @Email(regexp = ".+[@].+[\\.].+")
        String email,

        @NotEmpty(message = "required.phone.validation")
        @Length(min = 3, max = 14, message = "user.length.validation")
        String phone,

        @NotNull(message = "required.birth_date.validation")
        LocalDate birthDate,

        @NotEmpty(message = "required.password.validation")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "{password.rule}")
        @Length(min = 6, max = 50, message = "{size.validation}")
        String password,

        Boolean status
) {
}
