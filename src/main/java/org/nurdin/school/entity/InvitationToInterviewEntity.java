package org.nurdin.school.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "invitation_to_interview")
public class InvitationToInterviewEntity extends BaseEntity {
    private String  invitation;
    private String  interviewDate;


    public String getInvitation() {
        return invitation;
    }

    public void setInvitation(String invitation) {
        this.invitation = invitation;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    
}
