package com.arthurdrabazha.openmind.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
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

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    @Column(name = "password_digest")
    private String password;

    @Column(name = "delete_after_days")
    private Integer nonActiveDaysBeforeDelete;

    private Long likes;

    private Long dislikes;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private Boolean isEnabled;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createDate;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updateDate;

    @Column(name = "last_login_at")
    private Date lastLoginDate;

    @OneToMany
    @JoinColumn(name = "author_id")
    private List<Comment> comments;

    @OneToMany
    @JoinColumn(name = "author_id")
    private List<Post> posts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_categories",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    private List<Category> categories;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
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
        return isEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
