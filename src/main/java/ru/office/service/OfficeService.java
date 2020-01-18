package ru.office.service;

import ru.office.model.ReadRequest;
import ru.office.model.dto.BatchDto;
import ru.office.model.dto.OfficeDto;
import ru.office.model.entity.OfficeEntity;
import ru.office.util.NoEntryException;

import java.util.List;
import java.util.UUID;

public interface OfficeService {

    BatchDto<OfficeDto> findAll(ReadRequest readRequest);
    List<OfficeEntity> findAll();
    OfficeEntity findById(UUID id) throws NoEntryException;
    OfficeEntity save(OfficeEntity entity);
    void saveAll(List<OfficeEntity> entities);
    OfficeEntity update(OfficeDto dto, UUID id) throws NoEntryException;
    void delete(UUID id) throws NoEntryException;
    void costReduction();
}
