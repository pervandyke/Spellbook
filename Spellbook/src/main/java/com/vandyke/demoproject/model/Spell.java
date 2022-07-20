package com.vandyke.demoproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Spell {
    
    private Integer level;
    private String name;
    private String castingTime;
    private String range;
    private String damageAmount;
    private String damageType;
    private String components;
    private String duration;
    private String description;

}
