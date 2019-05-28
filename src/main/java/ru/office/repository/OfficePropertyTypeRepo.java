package ru.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.office.model.OfficePropertyTypeEntity;

public interface OfficePropertyTypeRepo extends JpaRepository<OfficePropertyTypeEntity, Long> {

    OfficePropertyTypeEntity findFirstById(Long id);

}
