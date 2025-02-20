package org.nurdin.school.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "form_reject_the_bids_for_study")
public class FormRejectTheBidForStudyEntity extends BaseEntity {
    @Column(name = "mail_for_reject_the_bid")
    private String mailForRejectTheBid;

    public String getMailForRejectTheBid() {
        return mailForRejectTheBid;
    }

    public void setMailForRejectTheBid(String mailForRejectTheBid) {
        this.mailForRejectTheBid = mailForRejectTheBid;
    }
}
