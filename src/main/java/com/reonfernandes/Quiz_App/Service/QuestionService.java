package com.reonfernandes.Quiz_App.Service;

import com.reonfernandes.Quiz_App.Model.Questions;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {
    ResponseEntity<String> addQuestion(Questions questions);

    ResponseEntity<List<Questions>> fetchData();

    ResponseEntity<String> deleteQuestion(int id);

    ResponseEntity<Questions> updateQuestion(Integer id, Questions questions);

    /*
    Additional Functionalities
     */
    ResponseEntity<Questions> fetchQuestionById(Integer id);

    ResponseEntity<List<Questions>> fetchByCategory(String category);

    ResponseEntity<List<Questions>> fetchByDifficultyLevel(String level);

    ResponseEntity<List<Questions>> fetchByCategoryAndDifficulty(String category, String level);
}
