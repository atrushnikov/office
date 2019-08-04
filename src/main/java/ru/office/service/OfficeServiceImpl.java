package ru.office.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.office.model.ReadRequest;
import ru.office.model.dto.OfficeDto;
import ru.office.model.entity.DepartmentEntity;
import ru.office.model.entity.OfficeCategoryEntity;
import ru.office.model.entity.OfficeEntity;
import ru.office.model.entity.OfficePropertyTypeEntity;
import ru.office.repository.OfficeRepo;
import ru.office.util.NoEntryException;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static ru.office.util.ResponseMsqEnum.NO_ENTRY;
import static ru.office.util.TableNamesEnum.OFFICE;

@Slf4j
@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {

    private OfficeRepo repo;
    private ModelMapper modelMapper;
    private OfficeCategoryService officeCategoryService;
    private OfficePropertyTypeService officePropertyTypeService;
    private DepartmentService departmentService;

    public OfficeServiceImpl(OfficeRepo repo, ModelMapper modelMapper, OfficeCategoryService officeCategoryService, OfficePropertyTypeService officePropertyTypeService, DepartmentService departmentService) {
        this.repo = repo;
        this.modelMapper = modelMapper;
        this.officeCategoryService = officeCategoryService;
        this.officePropertyTypeService = officePropertyTypeService;
        this.departmentService = departmentService;
    }


    @Override
    public List<OfficeEntity> findAll() {
        log.info("find all");
        return repo.findAll();
    }

    @Override
    public List<OfficeEntity> findAll(ReadRequest readRequest) {
        log.info("find all");
        PageRequest pageRequest = PageRequest.of(readRequest.getPage() - 1, readRequest.getSize());
        return repo.findAll(pageRequest).getContent();
    }

    @Override
    public OfficeEntity findById(UUID id) throws NoEntryException {
        log.info("findById with id : {}", id);
        OfficeEntity entity = repo.findFirstById(id);
        if (entity == null) {
            throw new NoEntryException(NO_ENTRY.getMessage(), id.toString(), OFFICE.getName());
        }
        return entity;
    }

    @Override
    public OfficeEntity save(OfficeEntity entity) {
        return  repo.saveAndFlush(entity);
    }

    @Override
    public void saveAll(List<OfficeEntity> entities) {
        repo.saveAll(entities);
    }

    @Override
    public OfficeEntity update(OfficeDto dto, UUID id) throws NoEntryException {
        OfficeEntity entity = findById(id);
        log.info("Updating {} with id : {}.\n\tOld entity :\n{}", OFFICE.getName(), id, modelMapper.map(entity, OfficeEntity.class));

        OfficeCategoryEntity officeCategoryEntity = officeCategoryService.findById(dto.getOfficeCategory().getId());
        OfficePropertyTypeEntity officePropertyTypeEntity = officePropertyTypeService.findById(dto.getOfficePropertyType().getId());
        Set<UUID> departmentIds = dto.getDepartments().stream()
                .map(DepartmentEntity::getId)
                .collect(Collectors.toSet());
        Set<DepartmentEntity> departmentEntities = departmentService.findAllByIds(departmentIds);

        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setDepartments(departmentEntities);
        entity.setOfficeCategory(officeCategoryEntity);
        entity.setOfficePropertyType(officePropertyTypeEntity);
        entity.setValue(dto.getValue());
        entity = save(entity);
        log.info("Updated {} with id : {}.\n\tNew entity :\n{}", OFFICE.getName(), id, modelMapper.map(entity, OfficeEntity.class));

        return entity;
    }

    @Override
    public void delete(UUID id) throws NoEntryException {
        log.info("Fetching & Deleting {} with id {}", OFFICE.getName(), id);
        OfficeEntity entity = findById(id);
        repo.delete(entity);
    }

    @Override
    @Scheduled(cron = "0 0 23 * * ?")
    public void costReduction() {
        List<OfficeEntity> officeEntities = this.findAll();
        this.percentageReduction(officeEntities, 1);
        this.saveAll(officeEntities);

    }

    public void percentageReduction(List<OfficeEntity> officeEntities, double percent) {
        for (OfficeEntity entity : officeEntities) {
            Double value = entity.getValue();
            double percentValue = value * (percent / 100);
            entity.setValue(value - percentValue);
        }
    }

}
