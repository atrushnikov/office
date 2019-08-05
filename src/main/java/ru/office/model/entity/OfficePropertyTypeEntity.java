package ru.office.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "officePropertyType")
@Table(name = "office_property_type")
@Data
public class OfficePropertyTypeEntity {

    public OfficePropertyTypeEntity() {
    }

    public OfficePropertyTypeEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    private Long id;

    private String name;

}
