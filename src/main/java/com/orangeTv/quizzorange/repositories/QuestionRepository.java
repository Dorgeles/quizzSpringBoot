package com.orangeTv.quizzorange.repositories;

import com.orangeTv.quizzorange.entities.Question;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer>{
    
}
