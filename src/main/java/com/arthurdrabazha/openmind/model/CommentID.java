package com.arthurdrabazha.openmind.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentID implements Serializable {

    @ManyToOne
    private User author;

    @ManyToOne
    private Post post;
}
