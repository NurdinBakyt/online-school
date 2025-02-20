package org.nurdin.school.dto;

import jakarta.persistence.Column;

public class FormRejectTheBidForStudyDTO {
    private String mailForRejectTheBid;

    public String getMailForRejectTheBid() {
        return mailForRejectTheBid;
    }

    public void setMailForRejectTheBid(String mailForRejectTheBid) {
        this.mailForRejectTheBid = mailForRejectTheBid;
    }
}
