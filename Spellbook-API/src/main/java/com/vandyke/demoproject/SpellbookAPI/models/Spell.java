package com.vandyke.demoproject.SpellbookAPI.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vandyke.demoproject.SpellbookAPI.frontEndObjects.SpellData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "spells")
public class Spell {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private Integer level;
    private String name;
    private String castingTime;
    private String range;
    private String damageAmount;
    private String damageType;
    private String components;
    private String duration;
    private String save;

    @Column(name = "description", length = 600)
    private String description;

    public SpellData toData() {
        SpellData data = new SpellData();

        data.setId(this.getId());
        data.setUserId(this.getUser().getId());
        data.setLevel(this.getLevel());
        data.setName(this.getName());
        data.setCastingTime(this.getCastingTime());
        data.setRange(this.getRange());
        data.setDamageAmount(this.getDamageAmount());
        data.setDamageType(this.getDamageType());
        data.setComponents(this.getComponents());
        data.setDuration(this.getDuration());
        data.setSave(this.getSave());
        data.setDescription(this.getDescription());

        return data;
    }

}
