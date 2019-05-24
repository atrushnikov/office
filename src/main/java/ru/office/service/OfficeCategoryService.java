package ru.office.service;

import ru.office.model.OfficeCategoryEntity;

import java.util.List;

public interface OfficeCategoryService {

    List<OfficeCategoryEntity> findAll();
    OfficeCategoryEntity save(OfficeCategoryEntity entity);

}
