package com.codechallenge.starshipsky.controller;

import com.codechallenge.starshipsky.domain.entity.GameEngine;
import com.codechallenge.starshipsky.domain.service.MovementService;
import com.codechallenge.starshipsky.domain.entity.StarShipPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StarShipSkyController {
    @Value("${app.name}")
    private String name;

    @Value("${app.email}")
    private String email;

    private MovementService movement;

    @Autowired
    StarShipSkyController(MovementService movement) {
        this.movement = movement;
    }

    @RequestMapping(value = "/name", method = RequestMethod.POST)
    public ResponseEntity<StarShipPlayer> name() {
        StarShipPlayer starShipPlayer = new StarShipPlayer(this.name, this.email);
        return new ResponseEntity<>(starShipPlayer, HttpStatus.OK);
    }

    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public ResponseEntity<String> move(@RequestBody GameEngine gameEngine) {

        return new ResponseEntity<>(movement.getNextMovement(gameEngine),HttpStatus.OK);
    }
}
