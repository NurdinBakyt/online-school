package org.nurdin.school.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.nurdin.school.enums.StatusOfBid;

@Entity
@Table (name = "bids_for_study")
public class BidForStudyEntity extends BaseEntity {
    @Column(name = "bid_parent")
    private String bidParent;
    @Column(name = "bid_status")
    private StatusOfBid bidStatus;
    @Column(name = "child_birth_certificate")
    private String childBirthCertificate;
    @Column(name = "place_of_residence")
    private String placeOfResidence;
    @Column(name = "passport_parent")
    private String passportParent;
    @Column(name = "name_parent")
    private String nameParent;
    @Column(name = "surname_parent")
    private String surnameParent;
    @Column(name = "patronymic_parent")
    private String patronymicParent;
    @Column(name = "phone_number_parent")
    private String phoneNumberParent;
    @Column(name = "email_parent")
    private String emailParent;

    @Column(name = "child_medical_certificate_copy")
    private String childMedicalCertificateCopy;
    @Column(name = "child_medical_record")
    private String childMedicalRecord;
    @Column(name = "age_child")
    private String ageChild;
    @Column(name = "surname_child")
    private String surnameChild;
    @Column(name = "patronymic_child")
    private String patronymicChild;
    @Column(name = "child_class")
    private String childClass;

    public String getBidParent() {
        return bidParent;
    }

    public void setBidParent(String bidParent) {
        this.bidParent = bidParent;
    }

    public StatusOfBid getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(StatusOfBid bidStatus) {
        this.bidStatus = bidStatus;
    }

    public String getChildBirthCertificate() {
        return childBirthCertificate;
    }

    public void setChildBirthCertificate(String childBirthCertificate) {
        this.childBirthCertificate = childBirthCertificate;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    public String getPassportParent() {
        return passportParent;
    }

    public void setPassportParent(String passportParent) {
        this.passportParent = passportParent;
    }

    public String getNameParent() {
        return nameParent;
    }

    public void setNameParent(String nameParent) {
        this.nameParent = nameParent;
    }

    public String getSurnameParent() {
        return surnameParent;
    }

    public void setSurnameParent(String surnameParent) {
        this.surnameParent = surnameParent;
    }

    public String getPatronymicParent() {
        return patronymicParent;
    }

    public void setPatronymicParent(String patronymicParent) {
        this.patronymicParent = patronymicParent;
    }

    public String getPhoneNumberParent() {
        return phoneNumberParent;
    }

    public void setPhoneNumberParent(String phoneNumberParent) {
        this.phoneNumberParent = phoneNumberParent;
    }

    public String getEmailParent() {
        return emailParent;
    }

    public void setEmailParent(String emailParent) {
        this.emailParent = emailParent;
    }

    public String getChildMedicalCertificateCopy() {
        return childMedicalCertificateCopy;
    }

    public void setChildMedicalCertificateCopy(String childMedicalCertificateCopy) {
        this.childMedicalCertificateCopy = childMedicalCertificateCopy;
    }

    public String getChildMedicalRecord() {
        return childMedicalRecord;
    }

    public void setChildMedicalRecord(String childMedicalRecord) {
        this.childMedicalRecord = childMedicalRecord;
    }

    public String getAgeChild() {
        return ageChild;
    }

    public void setAgeChild(String ageChild) {
        this.ageChild = ageChild;
    }

    public String getSurnameChild() {
        return surnameChild;
    }

    public void setSurnameChild(String surnameChild) {
        this.surnameChild = surnameChild;
    }

    public String getPatronymicChild() {
        return patronymicChild;
    }

    public void setPatronymicChild(String patronymicChild) {
        this.patronymicChild = patronymicChild;
    }

    public String getChildClass() {
        return childClass;
    }

    public void setChildClass(String childClass) {
        this.childClass = childClass;
    }
}
