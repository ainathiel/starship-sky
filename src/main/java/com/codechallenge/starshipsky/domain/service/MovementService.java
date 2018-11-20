package com.codechallenge.starshipsky.domain.service;

import com.codechallenge.starshipsky.domain.entity.GameEngine;
import com.codechallenge.starshipsky.domain.entity.Map;
import com.codechallenge.starshipsky.domain.entity.Movement;
import com.codechallenge.starshipsky.domain.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MovementService {
    private MapBuilderService mapBuilderService;
    private ShootingService shootingService;

    @Autowired
    public MovementService(
            MapBuilderService mapBuilderService,
            ShootingService shootingService
    ) {
        this.mapBuilderService = mapBuilderService;
        this.shootingService = shootingService;
    }

    /**
     *
     * @return Movement Return next movement (up, down, left or right, fire-up, fire-down, fire-right or fire-left)
     */
    public Movement getNextMovement(GameEngine gameEngine) {
        Map map = mapBuilderService.build(gameEngine);

        String move = "";

        if(gameEngine.getPlayer().isFire()) {
            move = shootingService.toFire(map, gameEngine);
        } else if(move.isEmpty()) {
            move = this.moveToNeutralInvader(gameEngine, map);

            if(move.isEmpty()) {
                move = this.getToRandomPosition(gameEngine, map);
            }
        }

        /*for(int i = 0; i < map.getWidth(); i++) {
            for(int j = 0; j < map.getHeight(); j++) {
                System.out.print(map.getBoxes()[i][j]);
            }
            System.out.println();
        }*/

        return new Movement(move);
    }

    /**
     * Move to random position
     *
     * @param gameEngine game data
     * @param map map
     * @return  Return next movement (up, down, left or right)
     */
    private String getToRandomPosition(GameEngine gameEngine, Map map) {
        Player player = gameEngine.getPlayer();

        int rightPosition = player.getPosition().getX() + 1;
        int leftPosition = player.getPosition().getX() - 1;
        int upPosition = player.getPosition().getY() - 1;
        int downPosition = player.getPosition().getY() + 1;

        List<String> movements = new ArrayList<>();
        int total = 0;

        if(rightPosition < map.getWidth()
                && map.getBoxes()[player.getPosition().getY()][rightPosition].equals(Map.EMPTY)) {
            movements.add(Movement.MOVE_RIGHT);
            total++;
        }

        if(leftPosition >= 0
                && map.getBoxes()[player.getPosition().getY()][leftPosition].equals(Map.EMPTY)) {
            movements.add(Movement.MOVE_LEFT);
            total++;
        }

        if(upPosition >= 0
                && map.getBoxes()[upPosition][player.getPosition().getX()].equals(Map.EMPTY)) {
            movements.add(Movement.MOVE_UP);
            total++;
        }

        if(downPosition < map.getHeight()
                && map.getBoxes()[downPosition][player.getPosition().getX()].equals(Map.EMPTY)) {
            movements.add(Movement.MOVE_DOWN);
            total++;
        }

        if(total > 0) {
            Random number = new Random();

            return movements.get(number.nextInt(total));
        } else {
            return "";
        }
    }

    /**
     * Move to neutral invader
     *
     * @param gameEngine game data
     * @param map map
     * @return  Return next movement (up, down, left or right)
     */
    private String moveToNeutralInvader(GameEngine gameEngine, Map map) {
        Player player = gameEngine.getPlayer();

        int rightPosition = player.getPosition().getX() + 1;
        int leftPosition = player.getPosition().getX() - 1;
        int upPosition = player.getPosition().getY() - 1;
        int downPosition = player.getPosition().getY() + 1;

        String move = "";


        if(rightPosition < map.getWidth()
                && map.getBoxes()[player.getPosition().getY()][rightPosition].equals(Map.NEUTRAL_INVADER)) {
            move = Movement.MOVE_RIGHT;
        }

        if(leftPosition >= 0
                && map.getBoxes()[player.getPosition().getY()][leftPosition].equals(Map.NEUTRAL_INVADER)) {
            move = Movement.MOVE_LEFT;
        }

        if(upPosition >= 0
                && map.getBoxes()[upPosition][player.getPosition().getX()].equals(Map.NEUTRAL_INVADER)) {
            move = Movement.MOVE_UP;
        }

        if(downPosition < map.getHeight()
                && map.getBoxes()[downPosition][player.getPosition().getX()].equals(Map.NEUTRAL_INVADER)) {
            move = Movement.MOVE_DOWN;
        }

        return move;
    }
}
