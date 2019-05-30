package ru.office.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.office.model.entity.OfficeEntity;
import ru.office.repository.OfficeRepo;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class OfficeServiceImpl implements OfficeService {

    private OfficeRepo repo;

    public OfficeServiceImpl(OfficeRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<OfficeEntity> findAll() {
        log.info("find all");
        return repo.findAll();
    }

    @Override
    public OfficeEntity findOne(UUID id) {
        log.info("findOne with id : {}", id);
        return repo.findFirstById(id);
    }

    @Override
    public OfficeEntity save(OfficeEntity entity) {
        log.info("save");
        return  repo.saveAndFlush(entity);
    }

}
