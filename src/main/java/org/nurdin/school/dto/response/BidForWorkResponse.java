package org.nurdin.school.dto.response;

import org.nurdin.school.entity.BidForWorkEntity;
import org.nurdin.school.enums.StatusOfBid;

public class BidForWorkResponse extends BidForWorkEntity {
    //просто к BidForWorkEntity добавляю 1-о поле username
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


