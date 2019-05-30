package ru.office.model.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class OfficePropertyTypeDto {

    public OfficePropertyTypeDto() {
    }

    private Long id;

    private String name;

    @Override
    public String toString() {
        return "\t\tid : " + id + "\n" + "\t\tname : " + name;
    }
}
