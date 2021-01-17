package com.arthurdrabazha.openmind.dto;

import com.arthurdrabazha.openmind.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostDto {

    @NotNull
    private String topic;

    @NotNull
    private List<Category> categories;
}
