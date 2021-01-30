package com.arthurdrabazha.openmind.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentID implements Serializable {

    @Column(name = "author_id")
    @NotNull
    private Long authorId;

    @ManyToOne
    private Post post;
}
