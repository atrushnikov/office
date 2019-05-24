package ru.office.service;

import org.springframework.stereotype.Service;
import ru.office.model.OfficeCategoryEntity;
import ru.office.repository.OfficeCategoryRepo;

import java.util.List;

@Service
public class OfficeCategoryServiceImpl implements OfficeCategoryService {

    private OfficeCategoryRepo repo;

    public OfficeCategoryServiceImpl(OfficeCategoryRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<OfficeCategoryEntity> findAll() {
        return repo.findAll();
    }

    @Override
    public OfficeCategoryEntity save(OfficeCategoryEntity entity) {
        return repo.saveAndFlush(entity);
    }

}
