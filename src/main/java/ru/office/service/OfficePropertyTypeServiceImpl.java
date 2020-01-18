package ru.office.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.office.model.dto.OfficeCategoryDto;
import ru.office.model.dto.OfficePropertyTypeDto;
import ru.office.model.entity.OfficePropertyTypeEntity;
import ru.office.repository.OfficePropertyTypeRepo;
import ru.office.util.NoEntryException;

import java.util.List;

import static ru.office.util.ResponseMsqEnum.NO_ENTRY;
import static ru.office.util.TableNamesEnum.*;

@Slf4j
@Service
@Transactional
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
    public OfficePropertyTypeEntity findById(Long id) throws NoEntryException {
        OfficePropertyTypeEntity entity = repo.findFirstById(id);
        if (entity == null) {
            throw new NoEntryException(NO_ENTRY.getMessage(), id.toString(), OFFICE_PROPERTY_TYPE.getName());
        }
        return entity;
    }

    @Override
    public OfficePropertyTypeEntity save(OfficePropertyTypeEntity entity) {
        return repo.saveAndFlush(entity);
    }

    @Override
    public OfficePropertyTypeEntity update(OfficePropertyTypeDto dto, Long id) throws NoEntryException {
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
    public void delete(Long id) throws NoEntryException {
        log.info("Fetching & Deleting {} with id {}", OFFICE_PROPERTY_TYPE.getName(), id);
        OfficePropertyTypeEntity entity = findById(id);
        repo.delete(entity);
    }
}
