package com.arthurdrabazha.openmind.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity(name = "comments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @EmbeddedId
    private CommentID id;

    @NotBlank
    private String message;

    private Boolean isModified;
}

