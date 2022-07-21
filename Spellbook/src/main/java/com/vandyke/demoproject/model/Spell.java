package com.vandyke.demoproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Spell {
    
    private Long id;
    private Integer level;
    private String name;
    private String castingTime;
    private String range;
    private String damageAmount;
    private String damageType;
    private String components;
    private String duration;
    private String save;
    private String description;

}
