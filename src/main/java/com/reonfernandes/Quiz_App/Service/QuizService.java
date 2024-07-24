package com.reonfernandes.Quiz_App.Service;

import com.reonfernandes.Quiz_App.Model.QuestionWrapper;
import com.reonfernandes.Quiz_App.Model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {
    ResponseEntity<String> createQuiz(String category, String level, int noOfQuestions, String quizTitle) throws Exception;

    ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id);

    ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses);
}
