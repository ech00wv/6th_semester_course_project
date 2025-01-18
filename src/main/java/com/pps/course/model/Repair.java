package com.pps.course.model;

import jakarta.persistence.*;

@Entity
@NamedQuery(name = "Repair.findByAirplaneId", query = "SELECT r FROM Repair r WHERE r.airplane.id = :airplaneId")
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    public Repair(){};

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
