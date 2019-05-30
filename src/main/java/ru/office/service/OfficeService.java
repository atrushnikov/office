package ru.office.service;

import ru.office.model.entity.OfficeEntity;

import java.util.List;
import java.util.UUID;

public interface OfficeService {

    List<OfficeEntity> findAll();
    OfficeEntity findOne(UUID id);
    OfficeEntity save(OfficeEntity entity);

}
