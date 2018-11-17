package com.nhsoluciones.starshipsky.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
class Player {
    private UUID id;
    private String name;
    private Position position;
    private Previous previous;
    private Area area;
    private boolean fire;
}
