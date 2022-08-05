package com.vandyke.demoproject.frontEndObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpellData {
    private Long id;
    private Long userId;
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
