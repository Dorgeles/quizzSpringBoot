package com.orangeTv.quizzorange.controllers;

import java.util.List;

import com.orangeTv.quizzorange.dto.QuizzRequest;
import com.orangeTv.quizzorange.entities.Question;
import com.orangeTv.quizzorange.repositories.QuestionRepository;
import com.orangeTv.quizzorange.repositories.ResponseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizzController {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ResponseRepository responseRepository;

    @PostMapping("/api/createQuizz")
    public Question createQuizz(@RequestBody QuizzRequest request) {

        System.out.println(request.getQuestion().getResponses());
        return questionRepository.save(request.getQuestion());
    }

    @GetMapping("/api/quizzs")
    public List<Question> getAllQuizz() {
        return questionRepository.findAll();
    }
}
