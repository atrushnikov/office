package ru.office.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.office.model.dto.DepartmentDto;
import ru.office.model.dto.OfficeCategoryDto;
import ru.office.model.entity.DepartmentEntity;
import ru.office.repository.DepartmentRepo;
import ru.office.util.NoEntryException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static ru.office.util.ResponseMsqEnum.NO_ENTRY;
import static ru.office.util.TableNamesEnum.DEPARTMENT;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepo repo;
    private ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepo repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DepartmentEntity> findAll() {
        return repo.findAll();
    }

    @Override
    public DepartmentEntity findById(UUID id) throws NoEntryException {
        log.info("findById with id : {}", id);
        DepartmentEntity entity = repo.findFirstById(id);
        if (entity == null) {
            throw new NoEntryException(NO_ENTRY.getMessage(), id.toString(), DEPARTMENT.getName());
        }
        return entity;
    }

    @Override
    public DepartmentEntity save(DepartmentEntity entity) {
        return repo.saveAndFlush(entity);
    }

    @Override
    public DepartmentEntity update(DepartmentDto dto, UUID id) throws NoEntryException {
        DepartmentEntity entity = findById(id);
        log.info("Updating {} with id : {}.\n\tOld entity :\n{}", DEPARTMENT.getName(), id, modelMapper.map(entity, OfficeCategoryDto.class));

        if (StringUtils.isNoneEmpty(dto.getName())) {
            entity.setName(dto.getName());
        }
        entity = save(entity);
        log.info("Updated {} with id : {}.\n\tUpdated entity :\n{}", DEPARTMENT.getName(), id, modelMapper.map(entity, OfficeCategoryDto.class));
        return entity;
    }

    @Override
    public void delete(UUID id) throws NoEntryException {
        log.info("Fetching & Deleting {} with id {}", DEPARTMENT.getName(), id);
        DepartmentEntity entity = findById(id);
        repo.delete(entity);
    }

    @Override
    public Set<DepartmentEntity> findAllByIds(Set<UUID> ids) {
        return repo.findAllByIdIn(ids);
    }
}
