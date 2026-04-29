package com.quiz.modal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor // required by JPA
@AllArgsConstructor
public class Doctors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    private String specialization;
    private String department;

    @Column(nullable = false)
    private boolean available = true; // default value prevents NULL issues
}