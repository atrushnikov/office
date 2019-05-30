package ru.office.model.dto;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import ru.office.model.entity.DepartmentEntity;
import ru.office.model.entity.OfficeCategoryEntity;
import ru.office.model.entity.OfficePropertyTypeEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class OfficeDto {

    public OfficeDto() {
    }

    private UUID id;

    private String city;

    private String address;

    private OfficeCategoryDto officeCategory;

    private OfficePropertyTypeDto officePropertyType;

    private Long value;

    private Set<DepartmentEntity> departments = new HashSet();
}
