package com.orangeTv.quizzorange.controllers;

import java.util.List;
import java.util.Optional;

import com.orangeTv.quizzorange.entities.Player;
import com.orangeTv.quizzorange.exception.ApiRequestException;
import com.orangeTv.quizzorange.repositories.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/api/players")
    public ResponseEntity<Object> getAllPlayer() throws Exception {

        List<Player> players = playerRepository.findAll();
        try {
            if (players.isEmpty()) {
                throw new ApiRequestException("there is not player record for now");
            } else {
                return new ResponseEntity<>(players, HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new ApiRequestException("there is not player record for now");
        }
    }

    @PostMapping("/api/players")
    public ResponseEntity<Object> createPlayer(@RequestBody Player player) {
        Boolean result = player.getNumber().matches("[a-zA-Z]+");
        if (!result && player.getNumber().length()>= 8) {
            List<Player> foundPlayers = playerRepository.getUserByNumber(player.getNumber());
            try {
                if (foundPlayers.isEmpty()) {
                    playerRepository.save(player);
                    return new ResponseEntity<>(player, HttpStatus.OK);
                } else {
                    throw new ApiRequestException("Plz check the requestbody");

                }
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.OK);
            }
        } else {
            System.out.println("++++++++++ When the result is not good" + result);
        }
        throw new ApiRequestException("Plz check the requestbody, or the lenght() of the given body");

    }

    @GetMapping("/api/players/{number}")
    public ResponseEntity<Object> getUserByNumber(@PathVariable("number") String number) {
        try {
            List<Player> foundPlayers = playerRepository.getUserByNumber(number);
            if (foundPlayers.isEmpty()) {
                throw new ApiRequestException("Player with " + number + "as number is not found");
            } else {
                return new ResponseEntity<>(foundPlayers, HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new ApiRequestException("This error occur" + e);
        }

    }

    @PutMapping("/api/players/{number}")
    public ResponseEntity<Object> editByNumber(@PathVariable("number") String number, @RequestBody Player player) {
        if (player.getNumber().length() <= 10) {
            try {
                List<Player> foundPlayers = playerRepository.getUserByNumber(number);
                if (foundPlayers.isEmpty()) {
                    throw new ApiRequestException("Player with " + number + "as number is not found");
                } else if(player.getNumber().length()>= 8 && !player.getNumber().matches("[a-zA-Z]+")) {
                    Player editPlayer = foundPlayers.get(0);
                    editPlayer.setNumber(player.getNumber());
                    Player savePlayer = playerRepository.save(editPlayer);
                    return new ResponseEntity<>(savePlayer, HttpStatus.OK);
                }
            } catch (Exception e) {
                throw new ApiRequestException("This error occur" + e);
            }
        } else {
            throw new ApiRequestException("Please give a proper number, check the body or the urlPath");
        }
        return null;

    }

    @DeleteMapping("/api/players/delete/{id}")
    public ResponseEntity<Object> deleteByID(@PathVariable("id") int id) {
        Optional<Player> player = playerRepository.findById(id);
        try {
            if (player.isPresent()) {
                playerRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new ApiRequestException("player with this id does not exist");
            }

        } catch (Exception e) {
            throw new ApiRequestException("This error occur " + e);
        }
    }
}
