package com.codechallenge.starshipsky.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Board {
    private Size size;
    private List<Walls> walls;
}
