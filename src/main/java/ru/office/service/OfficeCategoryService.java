package ru.office.service;

import ru.office.model.dto.OfficeCategoryDto;
import ru.office.model.entity.OfficeCategoryEntity;

import java.util.List;

public interface OfficeCategoryService {

    List<OfficeCategoryEntity> findAll();
    OfficeCategoryEntity findById(Long id);
    OfficeCategoryEntity save(OfficeCategoryEntity entity);
    OfficeCategoryEntity update(OfficeCategoryDto dto, Long id);
    void delete(Long id);
}
