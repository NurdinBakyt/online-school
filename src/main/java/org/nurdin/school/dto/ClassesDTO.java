package org.nurdin.school.dto;


import java.util.List;

public class ClassesDTO {
    private Long id;
    /**
     * Это поле классного руководителя
     */
    private ClassTeacherDTO classTeacher;
    /**
     * Это поле список студентов в классе
     */
    private List<StudentDTO> studentList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassTeacherDTO getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(ClassTeacherDTO classTeacher) {
        this.classTeacher = classTeacher;
    }

    public List<StudentDTO> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentDTO> studentList) {
        this.studentList = studentList;
    }
}
