package com.arthurdrabazha.openmind.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {

    @NotNull(message = "Your old password should be set")
    private String oldPassword;

    @NotNull(message = "New password should be set")
    @Size(min = 8, max = 255)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,255}$",
            message = "Password should contain at least one upper and lower case character, " +
                    "digit and special character.")
    private String newPassword;

    @NotNull(message = "Password repeat should be set")
    private String newPasswordRepeat;

}
