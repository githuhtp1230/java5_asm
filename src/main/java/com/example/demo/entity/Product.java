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
@Table(name = "products", schema = "java5_asm")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Size(max = 500)
    @NotNull
    @Column(name = "name", nullable = false, length = 500)
    private String name;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @Size(max = 500)
    @Column(name = "image", length = 500)
    private String image;

    @Column(name = "review")
    private Integer review;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @ColumnDefault("0")
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

}