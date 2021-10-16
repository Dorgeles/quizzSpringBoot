package com.orangeTv.quizzorange.repositories;
import java.util.List;

import com.orangeTv.quizzorange.entities.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends JpaRepository<Player, Integer>{
    @Query("select u from Player u where u.number=:c")
    List<Player> getUserByNumber(@Param("c") String c);
}
