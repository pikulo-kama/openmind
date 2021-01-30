package com.arthurdrabazha.openmind.dto;

import com.arthurdrabazha.openmind.model.Category;
import com.arthurdrabazha.openmind.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    @NotBlank(message = "Username should be set")
    @Pattern(regexp = "^[0-9a-z\\-]{2,25}$",
            message = "Username can only contain digits, lower case letters and dash symbol")
    private String username;

    @NotBlank(message = "E-mail should be set")
    @Email
    private String email;

    @NotBlank(message = "Password should be set")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password should contain at least 8 characters and contain digits," +
                    " special characters, lower and upper case letters"
    )
    private String password;

    @NotBlank(message = "Repeat of password should be set")
    private String repeatPassword;

    @NotNull(message = "Birth date should be set")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @Past
    private LocalDate birthDate;

    @NotNull
    private DeletePeriod deletePeriod;

    private List<Category> categories;

    @NotNull(message = "Select role")
    private UserRole role;
}
