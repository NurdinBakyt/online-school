package org.nurdin.school.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "rejection_of_the_bid_entity")
public class RejectionOfTheBidEntity extends BaseEntity {

    @Column(name = "email")
    private String  mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}