package com.arthurdrabazha.openmind.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity(name = "users")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

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

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "delete_after_days")
    private Integer nonActiveDaysBeforeDelete;

    private Boolean isEnabled;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastLoginDate = this.lastLoginDate
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        long daysFromLastLogin = ChronoUnit.DAYS.between(now, lastLoginDate);

        return daysFromLastLogin < nonActiveDaysBeforeDelete;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
