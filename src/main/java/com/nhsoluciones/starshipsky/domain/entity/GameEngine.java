package com.nhsoluciones.starshipsky.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameEngine {
    private Game game;
    private Player player;
    private Board board;
    private List<Players> players;
    private List<Invaders> invaders;
}
