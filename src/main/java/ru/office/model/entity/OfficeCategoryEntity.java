package ru.office.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity(name = "officeCategory")
@Table(name = "office_category")
public class OfficeCategoryEntity {

    public OfficeCategoryEntity() {
    }

    public OfficeCategoryEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    private Long id;

    private String name;

}
