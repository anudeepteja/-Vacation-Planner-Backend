package com.vacation.vacationPlanner.entity;

import jakarta.persistence.*;
import jakarta.persistence.*;

@Entity
@Table(name = "tripproposal")  // match exact table name
public class TripProposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "place_name", nullable = false, length = 150)
    private String placeName;

    @Column(name = "cost_per_person", nullable = false)
    private Double costPerPerson;

    @Column(name = "description", length = 2000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "proposed_by", nullable = false)
    private User proposedBy;


    @ManyToOne
    @JoinColumn(name = "group_id" , nullable = false)
    private TripGroup group;

    // --- Getters & Setters ---
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Double getCostPerPerson() {
        return costPerPerson;
    }

    public void setCostPerPerson(Double costPerPerson) {
        this.costPerPerson = costPerPerson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getProposedBy() {
        return proposedBy;
    }

    public void setProposedBy(User proposedBy) {
        this.proposedBy = proposedBy;
    }

    public TripGroup getGroup() {
        return group;
    }

    public void setGroup(TripGroup group) {
        this.group = group;
    }
}
