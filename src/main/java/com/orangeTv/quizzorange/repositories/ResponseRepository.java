package com.orangeTv.quizzorange.repositories;

import com.orangeTv.quizzorange.entities.Response;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Integer>{
    
}
