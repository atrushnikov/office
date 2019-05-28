package ru.office.model;

import lombok.Data;

@Data
public class OfficeCategoryDto {

    private Long id;

    private String name;

    @Override
    public String toString() {
        return "\t\tid : " + id + "\n" + "\t\tname : " + name;
    }

}
