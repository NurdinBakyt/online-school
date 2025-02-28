package org.nurdin.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "directors")
public class DirectorEntity extends BaseEntity{
    private String name;
    private String surname;
    private String email;

    @OneToMany(mappedBy = "directorEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<EmployeeEntity> employeeEntities;

    public DirectorEntity() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<EmployeeEntity> getEmployeeEntities() {
        return employeeEntities;
    }

    public void setEmployeeEntities(List<EmployeeEntity> employeeEntities) {
        this.employeeEntities = employeeEntities;
    }

    @Override
    public String toString() {
        return "DirectorEntity{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", employeeEntities=" + employeeEntities +
                ", id=" + id +
                '}';
    }
}
