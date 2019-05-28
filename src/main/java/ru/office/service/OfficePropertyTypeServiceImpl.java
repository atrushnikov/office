package ru.office.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.office.model.OfficeCategoryDto;
import ru.office.model.OfficePropertyTypeDto;
import ru.office.model.OfficePropertyTypeEntity;
import ru.office.repository.OfficePropertyTypeRepo;

import java.util.List;

@Slf4j
@Service
public class OfficePropertyTypeServiceImpl implements OfficePropertyTypeService {

    private OfficePropertyTypeRepo repo;
    private ModelMapper modelMapper;

    public OfficePropertyTypeServiceImpl(OfficePropertyTypeRepo repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OfficePropertyTypeEntity> findAll() {
        return repo.findAll();
    }

    @Override
    public OfficePropertyTypeEntity findById(Long id) {
        return repo.findFirstById(id);
    }

    @Override
    public OfficePropertyTypeEntity save(OfficePropertyTypeEntity entity) {
        return repo.saveAndFlush(entity);
    }

    @Override
    public OfficePropertyTypeEntity update(OfficePropertyTypeDto dto, Long id) {
        OfficePropertyTypeEntity entity = findById(id);
        log.info("Updating office_property_type with id : {}.\n\tOld entity :\n{}", id, modelMapper.map(entity, OfficeCategoryDto.class));

        if (StringUtils.isNoneEmpty(dto.getName())) {
            entity.setName(dto.getName());
        }
        entity = save(entity);
        log.info("Updated office_property_type with id : {}.\n\tUpdated entity :\n{}", id, modelMapper.map(entity, OfficeCategoryDto.class));
        return entity;
    }

    @Override
    public void delete(Long id) {
        OfficePropertyTypeEntity entity = repo.findFirstById(id);
        repo.delete(entity);
    }
}
