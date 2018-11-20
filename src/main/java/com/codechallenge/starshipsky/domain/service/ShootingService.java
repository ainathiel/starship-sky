package com.codechallenge.starshipsky.domain.service;

import com.codechallenge.starshipsky.domain.entity.*;
import org.springframework.stereotype.Service;

@Service
public class ShootingService {

    /**
     *
     * @param map Map
     * @param gameEngine game data
     * @return Movement Return next movement (fire-up, fire-down, fire-right or fire-left)
     */
    public String toFire(Map map, GameEngine gameEngine) {
        String move;

        move = this.getDirectionOfFire(map, gameEngine, Map.OTHER_PLAYER);

        if(move.isEmpty()) {
            move = this.getDirectionOfFire(map, gameEngine, Map.INVADER);
        }

        return move;
    }

    /**
     * Get direction of fire
     *
     * @param map map
     * @param gameEngine game data
     * @param target other player or invader
     * @return Movement return next movement (fire-up, fire-down, fire-right or fire-left)
     */
    private String getDirectionOfFire(
            Map map,
            GameEngine gameEngine,
            String target) {

        int currentPositionX = gameEngine.getPlayer().getPosition().getX();
        int currentPositionY = gameEngine.getPlayer().getPosition().getY();
        Area visibleArea = gameEngine.getPlayer().getArea();

        String move = "";

        for(int x = 0; x < currentPositionX; x++) {
            if(map.getBoxes()[currentPositionY][x].equals(target)) {
                move = Movement.MOVE_FIRE_LEFT;
                break;
            }
        }

        for(int y = 0; y < currentPositionY; y++) {
            if(map.getBoxes()[y][currentPositionX].equals(target)) {
                move = Movement.MOVE_FIRE_UP;
                break;
            }
        }

        int rightSide = visibleArea.getX2() - currentPositionX;

        for(int x = 0; x < rightSide; x++) {
            if(map.getBoxes()[currentPositionY][x].equals(target)) {
                move = Movement.MOVE_FIRE_LEFT;
                break;
            }
        }

        int downSide = visibleArea.getY2() - currentPositionY;

        for(int y = 0; y < downSide; y++) {
            if(map.getBoxes()[y][currentPositionX].equals(target)) {
                move = Movement.MOVE_FIRE_DOWN;
                break;
            }
        }

        return move;
    }
}
