package com.orangeTv.quizzorange.controllers;

import java.util.List;
import java.util.Optional;

import com.orangeTv.quizzorange.dto.QuizzRequest;
import com.orangeTv.quizzorange.entities.Question;
import com.orangeTv.quizzorange.exception.ApiRequestException;
import com.orangeTv.quizzorange.repositories.QuestionRepository;
import com.orangeTv.quizzorange.repositories.ResponseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizzController {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ResponseRepository responseRepository;

    @PostMapping("/api/create/quizzs")
    public Question createQuizz(@RequestBody QuizzRequest request) {
        return questionRepository.save(request.getQuestion());
    }

    @GetMapping("/api/quizzs")
    public List<Question> getAllQuizz() {
        return questionRepository.findAll();
    }
    @PutMapping("/api/quizz/edit/{id}")
    public ResponseEntity<Object> editByNumber(@PathVariable("id") int id, @RequestBody QuizzRequest request) {
        Optional<Question> findQuestion = questionRepository.findById(id);
        if (findQuestion.isPresent()) {
            Question editedQuestion = findQuestion.get();
            editedQuestion = request.getQuestion();
            questionRepository.save(editedQuestion);
            return new ResponseEntity<>(questionRepository.save(editedQuestion), HttpStatus.OK);
        } else {
            throw new ApiRequestException("quizz not found");
        }
    }
}
