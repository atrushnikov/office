package ru.office.service;

import ru.office.model.OfficePropertyTypeDto;
import ru.office.model.OfficePropertyTypeEntity;

import java.util.List;

public interface OfficePropertyTypeService {

    List<OfficePropertyTypeEntity> findAll();
    OfficePropertyTypeEntity findById(Long id);
    OfficePropertyTypeEntity save(OfficePropertyTypeEntity entity);
    OfficePropertyTypeEntity update(OfficePropertyTypeDto dto, Long id);
    void delete(Long id);

}
