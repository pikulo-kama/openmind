package com.arthurdrabazha.openmind.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserPasswordDto {

    @NotBlank(message = "Old password should be set")
    private String oldPassword;

    @NotBlank(message = "Password should be set")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password should contain at least 8 characters and contain digits," +
                    " special characters, lower and upper case letters")
    private String password;

    @NotBlank(message = "Repeat password should be set")
    private String passwordRepeat;

}
