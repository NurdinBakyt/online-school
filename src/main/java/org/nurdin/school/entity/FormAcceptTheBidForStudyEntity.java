package org.nurdin.school.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "form_accept_the_bids_for_study")
public class FormAcceptTheBidForStudyEntity extends BaseEntity{

    @Column(name = "mail_for_accept_bid")
    private String mailForAcceptBid;
    @Column(name = "meeting_date")
    private String meetingDate;

    public String getMailForAcceptBid() {
        return mailForAcceptBid;
    }

    public void setMailForAcceptBid(String mailForAcceptBid) {
        this.mailForAcceptBid = mailForAcceptBid;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }


}
