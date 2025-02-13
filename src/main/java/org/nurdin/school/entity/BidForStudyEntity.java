package org.nurdin.school.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


public class BidForStudyEntity extends BaseEntity {
    @Column(name = "infoOfChild")
    private String infoOfChild;
    @Column(name = "child'sDocuments")
    private String childsDocuments;
    @Column(name = "idDirector")
    private int idDirector;
    @Column(name = "statusOfBidConsideration")
    private Boolean statusOfBidConsideration;
    //имя , фамилия ,отчетво(не обязятельно)  - родителя
    //свидельство о рождении(ребенка) , место жительство (адрес) , заявление родителей(несколько паспортов это Мамин и Папин)
    //мед справка копия
    //мед книжка
    //
    //имя, фамилия ,отчетво(не обязятельно)
    //возраст
    //мед книжка (ребенка)
    //в какой класс пойдет()


}
