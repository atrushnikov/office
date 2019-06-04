package ru.office.service;

import ru.office.model.dto.OfficeDto;
import ru.office.model.entity.OfficeEntity;
import ru.office.util.NoEntryException;

import java.util.List;
import java.util.UUID;

public interface OfficeService {

    List<OfficeEntity> findAll();
    OfficeEntity findById(UUID id) throws NoEntryException;
    OfficeEntity save(OfficeEntity entity);
    OfficeEntity update(OfficeDto dto, UUID id) throws NoEntryException;
    void delete(UUID id) throws NoEntryException;
}
