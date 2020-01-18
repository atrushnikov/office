package ru.office.service;

import ru.office.model.dto.OfficePropertyTypeDto;
import ru.office.model.entity.OfficePropertyTypeEntity;
import ru.office.util.NoEntryException;

import java.util.List;

public interface OfficePropertyTypeService {

    List<OfficePropertyTypeEntity> findAll();
    OfficePropertyTypeEntity findById(Long id) throws NoEntryException;
    OfficePropertyTypeEntity save(OfficePropertyTypeEntity entity);
    OfficePropertyTypeEntity update(OfficePropertyTypeDto dto, Long id) throws NoEntryException ;
    void delete(Long id) throws NoEntryException;

}
