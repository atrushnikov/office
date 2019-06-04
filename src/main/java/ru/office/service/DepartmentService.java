package ru.office.service;

import ru.office.model.dto.DepartmentDto;
import ru.office.model.entity.DepartmentEntity;
import ru.office.util.NoEntryException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface DepartmentService {

    Set<DepartmentEntity> findAllByIds(Set<UUID> ids);
    List<DepartmentEntity> findAll();
    DepartmentEntity findById(UUID id) throws NoEntryException;
    DepartmentEntity save(DepartmentEntity entity);
    DepartmentEntity update(DepartmentDto dto, UUID id) throws NoEntryException;
    void delete(UUID id) throws NoEntryException;

}
