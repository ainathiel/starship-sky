package com.codechallenge.starshipsky.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movement {
    public static final String MOVE_UP = "up";
    public static final String MOVE_DOWN = "down";
    public static final String MOVE_LEFT = "left";
    public static final String MOVE_RIGHT = "right";
    public static final String MOVE_FIRE_UP = "fire-up";
    public static final String MOVE_FIRE_RIGHT = "fire-right";
    public static final String MOVE_FIRE_LEFT = "fire-left";
    public static final String MOVE_FIRE_DOWN = "fire-down";

    private String move;

    public Movement(String move) {
        this.move = move;
    }
}
