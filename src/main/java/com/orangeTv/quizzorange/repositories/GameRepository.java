package com.orangeTv.quizzorange.repositories;

import com.orangeTv.quizzorange.entities.Game;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer>{
    
}
