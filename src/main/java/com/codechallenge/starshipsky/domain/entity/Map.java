package com.codechallenge.starshipsky.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Map {
    public static final String EMPTY = "E";
    public static final String WALL = "W";
    public static final String SECURE_ZONE = "S";
    public static final String INVADER = "I";
    public static final String NEUTRAL_INVADER = "N";
    public static final String OTHER_PLAYER = "P";
    public static final String PLAYER_CURRENT_POSITION = "C";
    public static final String PLAYER_PREVIOUS_POSITION = "P";


    private int width;
    private int height;
    private String[][] boxes;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.boxes = new String[height][width];
        this.init();
    }

    /**
     * Initialize map
     */
    private void init() {
        for(int i = 0; i < this.getWidth(); i++) {
            for(int j = 0; j < this.getHeight(); j++) {
                this.getBoxes()[j][i] = EMPTY;
            }
        }
    }
}
