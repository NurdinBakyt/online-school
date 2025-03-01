package org.nurdin.school.dto;

import java.util.List;

public class ParentDTO {
    private Long id;
    private String name;
    private String surname;
    /**
     * Это родитель ученика.
     * Я сделал поле List т.к
     * У него возможно учатся несколько детей в школе
     */
    private List<StudentDTO> childrensList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<StudentDTO> getChildrensList() {
        return childrensList;
    }

    public void setChildrensList(List<StudentDTO> childrensList) {
        this.childrensList = childrensList;
    }
}
