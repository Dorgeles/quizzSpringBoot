package com.orangeTv.quizzorange.dto;

import com.orangeTv.quizzorange.entities.Question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuizzRequest {
    private Question question;

    public Question getQuestion() {
        return question;
    }

}
