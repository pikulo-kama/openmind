package com.arthurdrabazha.openmind.dto;

import com.arthurdrabazha.openmind.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {

    @NotNull(message = "Username should be set")
    @Pattern(regexp = "/[0-9a-z\\-]{2,25}/",
            message = "Username can only contain digits, lower case letters and dash symbol")
    private String username;

    private List<Category> categories;

    @NotNull
    private DeletePeriod deletePeriod;
}
