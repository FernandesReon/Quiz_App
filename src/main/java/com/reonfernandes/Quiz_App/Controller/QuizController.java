package com.reonfernandes.Quiz_App.Controller;

import com.reonfernandes.Quiz_App.Model.QuestionWrapper;
import com.reonfernandes.Quiz_App.Model.Response;
import com.reonfernandes.Quiz_App.Service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController{

    private final QuizService quizService;
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    /*
    Create a quiz
    Fetch Questions for quiz
    Calculate the score for quiz
     */

    // User can select the category, level of difficulty and number of questions for a quiz.
    @PostMapping("/createQuiz")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam String level,
                                             @RequestParam int noOfQuestions,
                                             @RequestParam String quizTitle){

        // Request Param is basically used to take user input like their choice of type they want to play.
        return quizService.createQuiz(category, level, noOfQuestions, quizTitle);
    }

    @GetMapping("/getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        /*
        Question Wrapper class is created to display only question and its option,
        if Questions are displayed then it will also show correct answer, and we
        don't want that kind of output.
         */
        return quizService.getQuizQuestion(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,
                                              @RequestBody List<Response> responses){
        return quizService.calculateScore(id, responses);
    }
}