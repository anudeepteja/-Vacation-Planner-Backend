package com.vacation.vacationPlanner.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProposalApprovalIdCK implements Serializable {

    private Integer userId;
    private Integer proposalId;

    public ProposalApprovalIdCK() {}

    public ProposalApprovalIdCK(Integer userId, Integer proposalId) {
        this.userId = userId;
        this.proposalId = proposalId;
    }

    // --- Getters & Setters ---
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProposalId() {
        return proposalId;
    }

    public void setProposalId(Integer proposalId) {
        this.proposalId = proposalId;
    }

    // equals & hashCode (VERY important for composite PK!)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProposalApprovalIdCK)) return false;
        ProposalApprovalIdCK that = (ProposalApprovalIdCK) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(proposalId, that.proposalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, proposalId);
    }
}
