package com.reonfernandes.Quiz_App.Repository;

import com.reonfernandes.Quiz_App.Model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository <Questions, Integer> {
    List<Questions> findByCategory(String category);
    List<Questions> findByDifficultyLevel(String level);
    List<Questions> findByCategoryAndDifficultyLevel(String category, String level);

    @Query(value = "SELECT * FROM questions question WHERE question.category = :category AND " +
            "question.difficulty_level = :level ORDER BY RAND() LIMIT :noOfQuestions", nativeQuery = true)
    List<Questions> findRandomQuestionsViaCategoryAndLevel(String category, String level, int noOfQuestions);
}
