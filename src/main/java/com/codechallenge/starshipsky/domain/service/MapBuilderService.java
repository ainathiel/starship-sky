package com.codechallenge.starshipsky.domain.service;

import com.codechallenge.starshipsky.domain.entity.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class MapBuilderService {
    static final String OPERATOR_PLUS = "+";
    static final String OPERATOR_SUBTRACTS = "-";


    Map build(GameEngine gameEngine) {

        Map map = new Map(
                gameEngine.getBoard().getSize().getWidth(),
                gameEngine.getBoard().getSize().getHeight()
        );

        this.setSecureArea(gameEngine, map);

        this.setWalls(gameEngine.getBoard().getWalls(), map);

        this.setInvaders(gameEngine.getInvaders(), map);

        this.setOtherPlayers(gameEngine.getPlayers(), map);

        this.setCurrentPosition(gameEngine.getPlayer(), map);

        this.setPreviousPosition(gameEngine.getPlayer(), map);

        return map;
    }

    /**
     * Set current position on the map
     *
     * @param player my starship
     * @param map map with my starship
     */
    private void setCurrentPosition(Player player, Map map) {
        map.getBoxes()[player.getPosition().getY()][player.getPosition().getX()] = Map.PLAYER_CURRENT_POSITION;
    }

    /**
     * Set previous position on the map
     *
     * @param player my starship
     * @param map map with my starship
     */
    private void setPreviousPosition(Player player, Map map) {
        map.getBoxes()[player.getPosition().getY()][player.getPosition().getX()] = Map.PLAYER_PREVIOUS_POSITION;
    }

    /**
     * Set other players on the map
     *
     * @param players players list
     * @param map map with other players
     */
    private void setOtherPlayers(List<Players> players, Map map) {
        for(Players player: players) {
            map.getBoxes()[player.getY()][player.getX()] = Map.OTHER_PLAYER;
        }
    }

    /**
     * Set invaders on the map
     *
     * @param invaders invaders list
     * @param map map with invaders
     */
    private void setInvaders(List<Invaders> invaders, Map map) {
        for (Invaders invader: invaders) {
            map.getBoxes()[invader.getY()][invader.getX()] = Map.INVADER;
        }
    }

    /**
     * Set wall on the map
     *
     * @param walls walls list
     * @param map map with walls
     */
    private void setWalls(List<Walls> walls, Map map) {
        for (Walls wall : walls) {
            map.getBoxes()[wall.getY()][wall.getX()] = Map.WALL;
        }
    }

    /**
     * Set secure area
     *
     * @param gameEngine game data
     * @param map map with walls
     */
    private void setSecureArea(GameEngine gameEngine, Map map) {
        Area visibleArea = gameEngine.getPlayer().getArea();

        int x1 = setLimit(visibleArea.getX1(), map.getWidth(), OPERATOR_PLUS);
        int y1 = setLimit(visibleArea.getY1(), map.getHeight(), OPERATOR_PLUS);
        int x2 = setLimit(visibleArea.getX2(), map.getWidth(), OPERATOR_SUBTRACTS);
        int y2 = setLimit(visibleArea.getY2(), map.getHeight(), OPERATOR_SUBTRACTS);

        for(int i = x1; i <= x2; i++) {
            map.getBoxes()[y1][i] = Map.SECURE_ZONE;
            map.getBoxes()[y2][i] = Map.SECURE_ZONE;
        }

        for(int j = y1; j <= y2; j++) {
            map.getBoxes()[j][x1] = Map.SECURE_ZONE;
            map.getBoxes()[j][x2] = Map.SECURE_ZONE;
        }
    }

    /**
     * Calculate limit to secure area
     *
     * @param coordinate int
     * @param limit int
     * @return x or y
     */
    private int setLimit(int coordinate, int limit, String operation) {
        int position = coordinate;

        switch (operation) {
            case OPERATOR_PLUS:
                    if(coordinate < limit - 1) {
                        position++;
                    } else {
                        position--;
                    }
                break;
            case OPERATOR_SUBTRACTS:
                    if(coordinate == 0) {
                        position++;
                    } else {
                        position--;
                    }
                break;
            default:
                break;
        }

        return position;
    }
}
