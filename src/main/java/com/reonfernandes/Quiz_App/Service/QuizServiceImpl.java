package com.reonfernandes.Quiz_App.Service;

import com.reonfernandes.Quiz_App.Model.QuestionWrapper;
import com.reonfernandes.Quiz_App.Model.Questions;
import com.reonfernandes.Quiz_App.Model.Quiz;
import com.reonfernandes.Quiz_App.Model.Response;
import com.reonfernandes.Quiz_App.Repository.QuestionRepository;
import com.reonfernandes.Quiz_App.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService{
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public ResponseEntity<String> createQuiz(String category, String level, int noOfQuestions, String quizTitle) {
        Quiz quiz = new Quiz();

        /*
         Questions will come from Questions repository, as we don't have pre-defined method to do so
         we are going to create a method and write native SQL query in question repository.
         */

        List<Questions> questions =
                questionRepository.findRandomQuestionsViaCategoryAndLevel(category, level, noOfQuestions);

        quiz.setQuizTitle(quizTitle);
        quiz.setQuestions(questions);

        // saving the entity quiz.
        quizRepository.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        // Now let's get the questions by id
        // Using Optional because if the quiz id that I am trying to fetch is not there in DB,it can give error.
        Optional<Quiz> quiz = quizRepository.findById(id);

        // Now we have fetched the questions from Database using quiz reference.
        List<Questions> questionsFromDB = quiz.get().getQuestions();

        /*
        Now we created new empty arraylist so that we can manually add the desired fields for question wrapper.
        We will use for each loop to get data from DB and pass as an argument to QuestionWrapper constructor.
         */
        List<QuestionWrapper> questionsForUsers = new ArrayList<>();

        for (Questions questions : questionsFromDB){
            QuestionWrapper questionWrapper = new QuestionWrapper(questions.getId(), questions.getQuestionTitle(),
                    questions.getOption1(), questions.getOption2(), questions.getOption3(), questions.getOption4());
            questionsForUsers.add(questionWrapper);
        }
        return new ResponseEntity<>(questionsForUsers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Questions> questions = quiz.getQuestions();
        int correctAnswers = 0;
        int index = 0;
        for (Response response : responses){
            if (response.getResponse().equals(questions.get(index).getCorrectAnswer()))
                correctAnswers++;
            index++;
        }

        return new ResponseEntity<>(correctAnswers, HttpStatus.OK);
    }
}