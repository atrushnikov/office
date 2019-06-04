package ru.office.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity(name = "department")
@Table(name = "department")
public class DepartmentEntity {

    @Id
    private UUID id;

    private String name;

}
