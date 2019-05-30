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

    @Id
    private Long id;

    private String name;

}
