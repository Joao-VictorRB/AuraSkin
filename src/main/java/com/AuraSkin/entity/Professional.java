package com.AuraSkin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; 

@Entity
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfessional;
    private String name;
    private String specialty;

    public Long getIdProfessional(){
        return idProfessional;
    }

    public void setIdProfessional(Long idProfessional){
        this.idProfessional = idProfessional;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSpecialty(){
        return specialty;
    }

    public void setSpecialty(String specialty){
        this.specialty = specialty;
    }
}
