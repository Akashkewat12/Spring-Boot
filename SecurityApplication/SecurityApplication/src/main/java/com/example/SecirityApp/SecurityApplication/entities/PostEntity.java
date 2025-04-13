package com.example.SecirityApp.SecurityApplication.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;


@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
}
