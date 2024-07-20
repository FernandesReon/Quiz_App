package com.reonfernandes.Quiz_App.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "Quiz_App")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Enter the question.")
    private String questionTitle;

    @NotBlank(message = "Option 1 field cannot be empty.")
    private String option1;

    @NotBlank(message = "Option 2 field cannot be empty.")
    private String option2;

    @NotBlank(message = "Option 3 field cannot be empty.")
    private String option3;

    @NotBlank(message = "Option 4 field cannot be empty.")
    private String option4;

    @NotBlank(message = "Correct Answer field cannot be empty.")
    private String correctAnswer;

    @NotBlank(message = "Enter level of difficulty.")
    @Length(max = 10, min = 4)
    private String difficultyLevel;

    @NotBlank(message = "Enter the category for the question.")
    private String category;
}
