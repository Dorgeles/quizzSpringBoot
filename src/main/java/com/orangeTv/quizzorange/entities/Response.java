package com.orangeTv.quizzorange.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.ToString;

@ToString
@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private Boolean isCorrect;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Boolean getIsCorrect() {
        return isCorrect;
    }
    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}
