package com.vacation.vacationPlanner.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "proposal_approval")
public class ProposalApproval {

    @EmbeddedId
    private ProposalApprovalIdCK id; // composite PK

    @ManyToOne
    @MapsId("userId")  // maps userId from composite key
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("proposalId") // maps proposalId from composite key
    @JoinColumn(name = "proposal_id", nullable = false)
    private TripProposal proposal;

    @Column(nullable = false, length = 20)
    private String status = "PENDING";  // APPROVED / REJECTED / PENDING

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;
    // handled automatically by DB DEFAULT CURRENT_TIMESTAMP ON UPDATE

    // --- Getters & Setters ---
    public ProposalApprovalIdCK getId() {
        return id;
    }

    public void setId(ProposalApprovalIdCK id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TripProposal getProposal() {
        return proposal;
    }

    public void setProposal(TripProposal proposal) {
        this.proposal = proposal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
