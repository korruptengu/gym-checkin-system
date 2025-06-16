package com.korruptengu.gymcheckinsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CourseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = true)
    private String description;
    @Column(nullable = false)
    private String category;

    public CourseType(String title, String category){
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title must not be null or blank");
        if (category == null || category.isBlank()) throw new IllegalArgumentException("Category must not be null or blank");
        this.title = title;
        this.category = category;
    }

    public CourseType(String title, String category, String description){
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title must not be null or blank");
        if (category == null || category.isBlank()) throw new IllegalArgumentException("Category must not be null or blank");
        if (description == null || description.isBlank()) throw new IllegalArgumentException("Description must not be null or blank");
        this.title = title;
        this.category = category;
        this.description = description;
    }

}
