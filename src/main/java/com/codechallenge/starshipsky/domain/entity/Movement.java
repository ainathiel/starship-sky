package com.codechallenge.starshipsky.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movement {
    private String move;

    public Movement(String move) {
        this.move = move;
    }
}
