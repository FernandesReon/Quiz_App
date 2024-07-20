package com.reonfernandes.Quiz_App.Controller;

import com.reonfernandes.Quiz_App.Model.Questions;
import com.reonfernandes.Quiz_App.Service.QuestionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuestionController {

//    @Autowired
    private final QuestionService questionService;
    /*
    QuestionService questionService = new QuestionServiceImpl();
      -- questionService will determine what all members of Service class can be accessed.
     */

    /*
    Simple logger
     */
    private final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // Add a new question.
    @PostMapping("/create")
    public ResponseEntity<String> addQuestion(@Valid @RequestBody Questions questions){
        LOGGER.info("Currently in addQuestion of QuestionController");
        return questionService.addQuestion(questions);
    }

    // Read the data.
    @GetMapping("/read")
    public ResponseEntity<List<Questions>> fetchData(){
        LOGGER.info("Currently in fetchData of QuestionController");
        return questionService.fetchData();
    }

    // Delete the data using id.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id){
        LOGGER.info("Currently in deleteQuestion of QuestionController");
        return questionService.deleteQuestion(id);
    }

    // Update the question data.
    @PutMapping("/update/{id}")
    public ResponseEntity<Questions> updateQuestion(@PathVariable Integer id,
                                    @RequestBody Questions questions){
        LOGGER.info("Currently in updateQuestion of QuestionController");
        return questionService.updateQuestion(id, questions);
    }

    /*
    Additional Controllers
    1. Get the data based on ID
    2. Get the data based on Category
    3. Get the data based on Difficulty level
    4. Get the data based on Category and Difficulty level
     */

    @GetMapping("/read/{id}")
    // Here the return type is Question object because, I want to fetch only single question data.
    public ResponseEntity<Questions> fetchQuestionById(@PathVariable Integer id){
        LOGGER.info("Currently in fetchQuestionById of QuestionController");
        return questionService.fetchQuestionById(id);
    }

    @GetMapping("/read/category/{category}")
    // Here I will return List of questions based on the category.
    public ResponseEntity<List<Questions>> fetchByCategory(@PathVariable String category){
        LOGGER.info("Currently in fetchByCategory of QuestionController");
        return questionService.fetchByCategory(category);
    }

    @GetMapping("/read/difficultyLevel/{level}")
    // Here I will return List of questions based on the difficulty level.
    public ResponseEntity<List<Questions>> fetchByDifficultyLevel(@PathVariable String level){
        LOGGER.info("Currently in fetchByDifficultyLevel of QuestionController");
        return questionService.fetchByDifficultyLevel(level);
    }

    @GetMapping("/read/{category}/{level}")
    public ResponseEntity<List<Questions>> fetchByCategoryAndDifficulty(@PathVariable String category,
                                                                        @PathVariable String level){
        LOGGER.info("Currently in fetchByCategoryAndDifficulty of QuestionController");
        return questionService.fetchByCategoryAndDifficulty(category, level);
    }
}
