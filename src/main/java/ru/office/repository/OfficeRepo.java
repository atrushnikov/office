package ru.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.office.model.entity.OfficeEntity;

import java.util.UUID;

public interface OfficeRepo extends JpaRepository<OfficeEntity, UUID>, JpaSpecificationExecutor<OfficeEntity> {

    OfficeEntity findFirstById(UUID id);

}
