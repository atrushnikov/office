package ru.office.model.dto;

import lombok.Data;
import ru.office.model.entity.OfficeEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class DepartmentDto {

    private UUID id;

    private String name;


}
