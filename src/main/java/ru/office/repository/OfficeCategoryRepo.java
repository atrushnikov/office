package ru.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.office.model.entity.OfficeCategoryEntity;

import java.util.UUID;

@Repository
public interface OfficeCategoryRepo extends JpaRepository<OfficeCategoryEntity, UUID>, JpaSpecificationExecutor {

    OfficeCategoryEntity findFirstById(Long id);

}
