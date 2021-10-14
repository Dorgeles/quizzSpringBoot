package com.orangeTv.quizzorange.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "quizz_id", referencedColumnName = "id")
    private Question quizz;

    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @OneToOne
    @JoinColumn(name = "player_response_id", referencedColumnName = "id")
    private Response response;

    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Question getQuizz() {
        return quizz;
    } 
    public void setQuizz(Question quizz) {
        this.quizz = quizz;
    }

    public Player getPlayer() {
        return player;
    } 
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Response getResponse() {
        return response;
    }
    public void setResponse(Response response){
        this.response = response;
    }
}
