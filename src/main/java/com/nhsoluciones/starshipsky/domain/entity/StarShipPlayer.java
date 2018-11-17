package com.nhsoluciones.starshipsky.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StarShipPlayer {

    private String name;

    private String email;

    public StarShipPlayer(
            String name,
            String email
    ){
        this.name = name;
        this.email = email;
    }
}
