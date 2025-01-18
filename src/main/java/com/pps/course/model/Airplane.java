package com.pps.course.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
public class Airplane {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "airplane")
    private List<Repair> repairs;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "airplane")
    private List<Sensor> sensors;

    public Airplane(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return "name: " + this.name + "desc: " + this.description;
    }
}
