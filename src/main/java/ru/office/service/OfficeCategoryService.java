package ru.office.service;

import ru.office.model.dto.OfficeCategoryDto;
import ru.office.model.entity.OfficeCategoryEntity;
import ru.office.util.NoEntryException;

import java.util.List;

public interface OfficeCategoryService {

    List<OfficeCategoryEntity> findAll();
    OfficeCategoryEntity findById(Long id) throws NoEntryException;
    OfficeCategoryEntity save(OfficeCategoryEntity entity);
    OfficeCategoryEntity update(OfficeCategoryDto dto, Long id) throws NoEntryException;
    void delete(Long id) throws NoEntryException;
}
