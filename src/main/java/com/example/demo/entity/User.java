package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "java5_asm")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 500)
    @NotNull
    @Column(name = "password", nullable = false, length = 500)
    private String password;

    @Size(max = 500)
    @Column(name = "avatar", length = 500)
    private String avatar;

    @Size(max = 500)
    @Column(name = "address", length = 500)
    private String address;

    @NotNull
    @Column(name = "role", nullable = false)
    private Boolean role = false;

    @ColumnDefault("0")
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new LinkedHashSet<>();

}