package com.arthurdrabazha.openmind.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @Email
    private String email;

    @Past
    private LocalDate birthDate;

    @NotBlank
    @Column(name = "password_digest")
    private String password;

    @Column(name = "delete_after_days")
    private Integer nonActiveDaysBeforeDelete;

    private Long likes;

    private Long dislikes;

    @Column(name = "created_at")
    private Date createDate;

    @Column(name = "updated_at")
    private Date updateDate;

    @Column(name = "last_login_at")
    private Date lastLoginDate;

    @OneToMany
    private List<Comment> comments;

    @OneToMany
    @JoinColumn(name = "author_id")
    private List<Post> posts;

    @ManyToMany(mappedBy = "users")
    private List<Category> categories;

//    @OneToMany
//    @JoinColumn(name = "user_id")
//    private List<UserPassword> oldPasswords;

}
