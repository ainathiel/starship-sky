package com.codechallenge.starshipsky.domain.service;

import com.codechallenge.starshipsky.domain.entity.GameEngine;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MovementService {
    private List<String> move;

    public MovementService() {
        this.move = new ArrayList<>();
        move.add("up");
        move.add("down");
        move.add("left");
        move.add("right");
        move.add("fire-up");
        move.add("fire-right");
        move.add("fire-left");
        move.add("fire-down");
    }

    /**
     *
     * @return String up, down, left or right, fire-up, fire-down, fire-right or fire-left
     */
    public String getNextMovement(GameEngine gameEngine) {
        Random number = new Random();
        return move.get(number.nextInt(8));
    }
}
