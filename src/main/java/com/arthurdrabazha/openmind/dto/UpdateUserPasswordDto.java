package com.arthurdrabazha.openmind.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserPasswordDto {

    @NotNull
    private String oldPassword;

    @NotNull
    private String password;

    @NotNull
    private String passwordRepeat;

}
