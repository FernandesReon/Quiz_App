package com.reonfernandes.Quiz_App.Service;

import com.reonfernandes.Quiz_App.Exceptions.QuestionNotFoundException;
import com.reonfernandes.Quiz_App.Model.Questions;
import com.reonfernandes.Quiz_App.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

//    @Autowired
    private final QuestionRepository repository;

    public QuestionServiceImpl(QuestionRepository repository) {
        this.repository = repository;
    }

    /*
        Basic CRUD operation
         */
    @Override
    public ResponseEntity<String> addQuestion(Questions questions) {
        repository.save(questions);
        return new ResponseEntity<>("Question Created Successfully.", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Questions>> fetchData() {
        List data = repository.findAll();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteQuestion(int id) {
        Optional<Questions> optionalQuestions = repository.findById(id);
        if (optionalQuestions.isPresent()){
            repository.deleteById(id);
            return new ResponseEntity<>("Question Deleted Successfully", HttpStatus.OK);
        }
        else{
            throw new QuestionNotFoundException("Id: "+ id + " not found");
        }
    }

    @Override
    public ResponseEntity<Questions> updateQuestion(Integer id, Questions questions) {
        Optional<Questions> optionalQuestions = repository.findById(id);
        if (optionalQuestions.isPresent()){
            Questions updatedQuestion = optionalQuestions.get();

            // Checking if the Object that we want to update is not null and not empty, then set the new value.

            // Update the question title
            if (Objects.nonNull(questions.getQuestionTitle()) && !"".equalsIgnoreCase(questions.getQuestionTitle())) {
                updatedQuestion.setQuestionTitle(questions.getQuestionTitle());
            }
        /*
        Update the options for question
         */

            // option 1
            if (Objects.nonNull(questions.getOption1()) && !"".equalsIgnoreCase(questions.getOption1())) {
                updatedQuestion.setOption1(questions.getOption1());
            }

            // option 2
            if (Objects.nonNull(questions.getOption2()) && !"".equalsIgnoreCase(questions.getOption2())) {
                updatedQuestion.setOption2(questions.getOption2());
            }

            // option 3
            if (Objects.nonNull(questions.getOption3()) && !"".equalsIgnoreCase(questions.getOption3())) {
                updatedQuestion.setOption3(questions.getOption3());
            }

            // option 4
            if (Objects.nonNull(questions.getOption4()) && !"".equalsIgnoreCase(questions.getOption4())) {
                updatedQuestion.setOption4(questions.getOption4());
            }

            // Update correct answer
            if (Objects.nonNull(questions.getCorrectAnswer()) && !"".equalsIgnoreCase(questions.getCorrectAnswer())) {
                updatedQuestion.setCorrectAnswer(questions.getCorrectAnswer());
            }

            // Update level of difficulty
            if (Objects.nonNull(questions.getDifficultyLevel()) && !"".equalsIgnoreCase(questions.getDifficultyLevel())) {
                updatedQuestion.setDifficultyLevel(questions.getDifficultyLevel());
            }

            // Update category of the question
            if (Objects.nonNull(questions.getCategory()) && !"".equalsIgnoreCase(questions.getCategory())) {
                updatedQuestion.setCategory(questions.getCategory());
            }

            return new ResponseEntity<>(repository.save(updatedQuestion), HttpStatus.OK);
        } else {
            throw new QuestionNotFoundException("Question with ID: "+ id + " not found");
        }
    }

    /*
    Additional Endpoints
     */
    @Override
    public ResponseEntity<Questions> fetchQuestionById(Integer id) {
        Optional<Questions> optionalQuestion = repository.findById(id);
        if (optionalQuestion.isPresent()){
            return new ResponseEntity<>(optionalQuestion.get(), HttpStatus.OK);
        }
        else {
            throw new QuestionNotFoundException("Question with ID: "+ id + " not found");
        }
    }

    @Override
    public ResponseEntity<List<Questions>> fetchByCategory(String category) {
        List<Questions> questions = repository.findByCategory(category);
        if (questions.isEmpty()){
            throw new QuestionNotFoundException("No Question found for category: "+ category);
        }
        else {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<Questions>> fetchByDifficultyLevel(String level) {
        List<Questions> questions = repository.findByDifficultyLevel(level);
        if (questions.isEmpty()){
            throw new QuestionNotFoundException("No such "+ level+ " difficulty level found: ");
        }
        else {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<Questions>> fetchByCategoryAndDifficulty(String category, String level) {

        List<Questions> categoryQuestion = repository.findByCategory(category);
        if (categoryQuestion.isEmpty()){
            throw new QuestionNotFoundException("No Question found for category: "+ category);
        }

        List<Questions> questions = repository.findByCategoryAndDifficultyLevel(category, level);
        if (questions.isEmpty()){
            throw new QuestionNotFoundException("No questions found for category: "+ category +
                    " and difficulty level: "+ level);
        }
        else {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
    }
}
