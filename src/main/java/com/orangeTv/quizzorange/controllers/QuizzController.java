package com.orangeTv.quizzorange.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.orangeTv.quizzorange.dto.QuizzRequest;
import com.orangeTv.quizzorange.entities.Question;
import com.orangeTv.quizzorange.exception.ApiRequestException;
import com.orangeTv.quizzorange.repositories.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class QuizzController {
    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/api/create/quizzs")
    public Question createQuizz(@RequestBody QuizzRequest request) {
        System.out.println(request.getQuestion().getResponses().get(1).getIsCorrect());
        System.out.println(request.getQuestion().getResponses().get(0).getIsCorrect());
        System.out.println(request.getQuestion().getResponses().get(2).getIsCorrect());
        return questionRepository.save(request.getQuestion());
    }

    @GetMapping("/api/quizzs")
    public List<Question> getAllQuizz() {
        return questionRepository.findAll();
    }

    @GetMapping("/api/quizzs/current")
    public Question getQuestionOfTheWeek() {
        List<Question> questions =  questionRepository.findAll();
        Question currentQuestion;
        for (Question question : questions) {
            Date todate = new Date();
            System.out.println("+++++++++++++++++++++++"+question.getEDate());
            System.out.println("+++++++++++++++++++++++"+question.getEDate());
            // 
            if (question.getSDate().before(todate) && question.getEDate().after(todate)) {
                currentQuestion = question;
                return currentQuestion;
            } else {
                throw new ApiRequestException("there is no record for now");
            }
        }
        return null;
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
    @DeleteMapping("/api/quizzs/delete/{id}")
    public ResponseEntity<Object> deleteByID(@PathVariable("id") int id) {
        Optional<Question> question = questionRepository.findById(id);
        try {
            if (question.isPresent()) {
                questionRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new ApiRequestException("player with this id does not exist");
            }

        } catch (Exception e) {
            throw new ApiRequestException("This error occur " + e);
        }
    }
}
