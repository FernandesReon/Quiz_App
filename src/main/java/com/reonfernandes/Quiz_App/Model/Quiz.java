package com.reonfernandes.Quiz_App.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Quiz")
public class Quiz {
    /*
    When a quiz is created
    1. It will have id
    2. A title for quiz
    3. List of Questions.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String quizTitle;
    private String category;

    // I have used this annotation because same question can be repeated in another quiz
    @ManyToMany
    private List<Questions> questions;
}