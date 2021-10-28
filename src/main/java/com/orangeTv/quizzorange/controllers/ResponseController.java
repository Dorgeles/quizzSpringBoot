package com.orangeTv.quizzorange.controllers;

import java.util.List;
import java.util.Optional;

import com.orangeTv.quizzorange.entities.Response;
import com.orangeTv.quizzorange.exception.ApiRequestException;
import com.orangeTv.quizzorange.repositories.ResponseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {
    @Autowired
    private ResponseRepository repo;

    @GetMapping("/api/responses")
    public ResponseEntity<Object> getAllResponse(){
        List<Response> responsesList = repo.findAll();
        try {
            if (responsesList.isEmpty()) {
                throw new ApiRequestException("there is not player record for now");
            } else {
                return new ResponseEntity<>(responsesList, HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new ApiRequestException("there is not player record for now");
        }
    }
    @GetMapping("/api/response/{id}")
    public ResponseEntity<Object> getResponseById(@PathVariable("id") int id){
        Optional<Response> responsesfound = repo.findById(id);
        try {
            if (responsesfound.isPresent()) {
                Response foundOne = responsesfound.get();
                return new ResponseEntity<>(foundOne, HttpStatus.OK);
            } else {
                throw new ApiRequestException("there is not Question record for now");
            }
        } catch (Exception e) {
            throw new ApiRequestException("there is not player record for now");
        }
    }

    @PutMapping("/api/edit/response/{id}")
    public ResponseEntity<Object> updateResponse(@PathVariable("id") int id) {
        Response foundresponse = repo.getById(id);
        if (foundresponse.getId() != 0) {
            Response editedResponse = repo.save(foundresponse);
            return new ResponseEntity<>(editedResponse, HttpStatus.OK);
        } else {
            throw new ApiRequestException("Please give a proper id, check the body or the urlPath");
        }
    }
}
