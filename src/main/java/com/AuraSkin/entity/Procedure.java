package com.AuraSkin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; // usado junto com o GeneratedValue


@Entity
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcedure;
    private String name;
    private String description;
    private Float price;
    private Integer durationMin; // Duration in minutes


    public Long getIdProcedure(){
        return idProcedure;
    }

    public void setIdProcedure(Long idProcedure){
        this.idProcedure = idProcedure;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Float getPrice(){
        return price;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public Integer getDurationMin(){
        return durationMin;
    }

    public void setDurationMin(int durationMin){
        this.durationMin = durationMin;
    }

}
