package com.reonfernandes.Quiz_App.Repository;

import com.reonfernandes.Quiz_App.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository <Quiz, Integer> {
}
