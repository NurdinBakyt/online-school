package org.nurdin.school.dto.response;

import org.nurdin.school.entity.BidForWorkEntity;

public class BidForWorkResponse extends BidForWorkEntity {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
