package org.nurdin.school.dto;

import java.util.Map;


public class ScheduleDTO {
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "id=" + id +
                '}';
    }
}
