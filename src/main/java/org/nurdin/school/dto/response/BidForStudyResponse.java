package org.nurdin.school.dto.response;

import org.nurdin.school.entity.BidForStudyEntity;

public class BidForStudyResponse extends BidForStudyEntity {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
