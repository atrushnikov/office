package ru.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.office.model.entity.OfficeEntity;

import java.util.UUID;

public interface OfficeRepo extends JpaRepository<OfficeEntity, UUID> {

    OfficeEntity findFirstById(UUID id);

}
