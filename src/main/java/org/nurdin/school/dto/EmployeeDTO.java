package org.nurdin.school.dto;

public class EmployeeDTO {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String photo;
    private String passportPhoto;
    private String resume;
    private int age;
    private DirectorDTO directorDTO;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPassportPhoto() {
        return passportPhoto;
    }

    public void setPassportPhoto(String passportPhoto) {
        this.passportPhoto = passportPhoto;
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

    public DirectorDTO getDirectorDTO() {
        return directorDTO;
    }

    public void setDirectorDTO(DirectorDTO directorDTO) {
        this.directorDTO = directorDTO;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", passportPhoto='" + passportPhoto + '\'' +
                ", resume='" + resume + '\'' +
                ", age=" + age +
                ", directorDTO=" + directorDTO +
                '}';
    }
}
