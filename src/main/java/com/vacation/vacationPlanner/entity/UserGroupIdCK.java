package com.vacation.vacationPlanner.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserGroupIdCK implements Serializable {
    private Integer userId;
    private Integer groupId;

    public UserGroupIdCK() {}

    public UserGroupIdCK(Integer userId, Integer groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    // --- Getters & Setters ---
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getGroupId() {
        return groupId;
    }
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    // equals() & hashCode() are REQUIRED for composite keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGroupIdCK)) return false;
        UserGroupIdCK that = (UserGroupIdCK) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, groupId);
    }
}
