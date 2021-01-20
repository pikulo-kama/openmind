//package com.arthurdrabazha.openmind.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.validation.constraints.NotBlank;
//
////@Entity(name = "user_passwords")
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Embeddable
//public class UserPassword {
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false, updatable = false)
//    private User user;
//
//    @Column(name = "old_password_digest")
//    @NotBlank
//    private String password;
//}
