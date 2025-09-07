package com.vacation.vacationPlanner.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_group")
public class UserGroup {

    @EmbeddedId
    private UserGroupIdCK id;  // composite PK

    @ManyToOne
    @MapsId("userId") // maps userId inside UserGroupId
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("groupId") // maps groupId inside UserGroupId
    @JoinColumn(name = "group_id", nullable = false)
    private TripGroup group;

    @Column(nullable = false, length = 20)
    private String role = "MEMBER";  // default

    // --- Getters & Setters ---
    public UserGroupIdCK getId() {
        return id;
    }

    public void setId(UserGroupIdCK id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TripGroup getGroup() {
        return group;
    }

    public void setGroup(TripGroup group) {
        this.group = group;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
