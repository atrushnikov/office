package ru.office.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.office.model.dto.OfficeCategoryDto;
import ru.office.model.entity.OfficeCategoryEntity;
import ru.office.repository.OfficeCategoryRepo;

import java.util.List;

@Slf4j
@Service
public class OfficeCategoryServiceImpl implements OfficeCategoryService {

    private OfficeCategoryRepo repo;
    private ModelMapper modelMapper;

    public OfficeCategoryServiceImpl(OfficeCategoryRepo repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OfficeCategoryEntity> findAll() {
        return repo.findAll();
    }

    @Override
    public OfficeCategoryEntity findById(Long id) {
        return repo.findFirstById(id);
    }

    @Override
    public OfficeCategoryEntity save(OfficeCategoryEntity entity) {
        return repo.saveAndFlush(entity);
    }

    @Override
    public OfficeCategoryEntity update(OfficeCategoryDto dto, Long id) {
        OfficeCategoryEntity entity = findById(id);
        log.info("Updating office_category with id : {}.\n\tOld entity :\n{}", id, modelMapper.map(entity, OfficeCategoryDto.class));

        if (StringUtils.isNoneEmpty(dto.getName())) {
            entity.setName(dto.getName());
        }
        entity = save(entity);
        log.info("Updated office_category with id : {}.\n\tUpdated entity :\n{}", id, modelMapper.map(entity, OfficeCategoryDto.class));
        return entity;
    }

    @Override
    public void delete(Long id) {
        OfficeCategoryEntity entity = repo.findFirstById(id);
        repo.delete(entity);
    }

}
