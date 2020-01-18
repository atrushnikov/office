package ru.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.office.model.entity.DepartmentEntity;

import java.util.Set;
import java.util.UUID;

public interface DepartmentRepo extends JpaRepository<DepartmentEntity, UUID>, JpaSpecificationExecutor {

    Set<DepartmentEntity> findAllByIdIn(Set<UUID> ids);
    DepartmentEntity findFirstById(UUID id);

}
