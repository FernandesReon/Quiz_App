package com.reonfernandes.Quiz_App.Repository;

import com.reonfernandes.Quiz_App.Model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository <Questions, Integer> {
    List<Questions> findByCategory(String category);
    List<Questions> findBydifficultyLevel(String level);
    List<Questions> findByCategoryAndDifficultyLevel(String category, String level);
}
