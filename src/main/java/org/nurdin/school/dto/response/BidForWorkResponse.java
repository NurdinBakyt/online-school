package org.nurdin.school.dto.response;

import org.nurdin.school.entity.BidForWorkEntity;
import org.nurdin.school.enums.StatusOfBid;

public class BidForWorkResponse extends BidForWorkEntity {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
