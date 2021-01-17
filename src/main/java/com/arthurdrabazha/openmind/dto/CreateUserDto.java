package com.arthurdrabazha.openmind.dto;

import com.arthurdrabazha.openmind.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    @NotNull(message = "Username should be set")
    @Pattern(regexp = "/[0-9a-z\\-]{2,25}/",
            message = "Username can only contain digits, lower case letters and dash symbol")
    private String username;

    @NotNull(message = "E-mail should be set")
    @Email
    private String email;

    @Min(value = 12, message = "Password should contain at least 12 characters")
    @NotNull(message = "Password should be set")
    @Pattern(regexp = "/(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,}/",
            message = "Password should contain at least 12 characters and contain digits," +
                    " special characters, lower and upper case letters"
             )
    private String password;

    @NotNull(message = "Repeat of password should be set")
    private String repeatPassword;

    @NotNull(message = "Birth date should be set")
    @Past
    private LocalDateTime birthDate;

    private List<Category> categories;

}
