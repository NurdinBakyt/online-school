package org.nurdin.school.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.nurdin.school.enums.EmployeeStatus;

import java.time.LocalDateTime;

// Таблица которая описывает данные о пользователях которые хотят трудоустроится
// в школу.
@Entity
@Table(name = "job_applications")
public class EmployeeEntity extends BaseEntity {
    private String name;
    private String surname;
    private String phone;
    private String resume;
    private int age;
    private String email;
    @Schema(description = " дата добавления")
    private LocalDateTime createdAt;

    @Column(name = "employee_status")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Статус нового сотрудника, по типу принят или отклонён; перевод (APPROVED : ОДОБРЕННЫЙ) ( REJECTED : ОТКЛОНЕННЫЙ)")
    private EmployeeStatus employeeStatus;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private DirectorEntity directorEntity;

    public EmployeeEntity() {
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public DirectorEntity getDirectorEntity() {
        return directorEntity;
    }

    public void setDirectorEntity(DirectorEntity directorEntity) {
        this.directorEntity = directorEntity;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", resume='" + resume + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", employeeStatus=" + employeeStatus +
                ", directorEntity=" + directorEntity +
                ", id=" + id +
                '}';
    }
}
