package com.codechallenge.starshipsky.domain.service;

import com.codechallenge.starshipsky.domain.entity.GameEngine;
import com.codechallenge.starshipsky.domain.entity.Movement;
import com.codechallenge.starshipsky.domain.entity.Player;
import com.codechallenge.starshipsky.domain.entity.Walls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MovementService {
    private static final String MOVE_UP = "up";
    private static final String MOVE_DOWN = "down";
    private static final String MOVE_LEFT = "left";
    private static final String MOVE_RIGHT = "right";
    private static final String MOVE_FIRE_UP = "fire-up";
    private static final String MOVE_FIRE_RIGHT = "fire-right";
    private static final String MOVE_FIRE_LEFT = "fire-left";
    private static final String MOVE_FIRE_DOWN = "fire-down";

    private List<String> move;
    private BoardBuilder boardBuilder;

    @Autowired
    public MovementService(BoardBuilder boardBuilder) {
        this.boardBuilder = boardBuilder;
        this.move = new ArrayList<>();
        move.add(MOVE_UP);
        move.add(MOVE_DOWN);
        move.add(MOVE_LEFT);
        move.add(MOVE_RIGHT);
        move.add(MOVE_FIRE_UP);
        move.add(MOVE_FIRE_DOWN);
        move.add(MOVE_FIRE_LEFT);
        move.add(MOVE_FIRE_RIGHT);
    }

    /**
     *
     * @return String up, down, left or right, fire-up, fire-down, fire-right or fire-left
     */
    public Movement getNextMovement(GameEngine gameEngine) {
       /* Player player = gameEngine.getPlayer();

        int rightPosition = player.getPosition().getX() + 1;
        int leftPosition = player.getPosition().getX() - 1;
        int upPosition = player.getPosition().getY() + 1;
        int downProsition = player.getPosition().getY() - 1;*/
        //String[][] board = boardBuilder.build(gameEngine);

        /*for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }*/

        Random number = new Random();
        return new Movement(move.get(number.nextInt(8)));
    }
}
