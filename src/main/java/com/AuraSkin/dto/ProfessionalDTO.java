package com.AuraSkin.dto;

import jakarta.validation.constraints.NotBlank;

public class ProfessionalDTO {
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Specialty is required")
    private String specialty;

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
