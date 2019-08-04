package ru.office.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.office.model.dto.DepartmentDto;
import ru.office.model.dto.OfficeDto;
import ru.office.model.entity.DepartmentEntity;
import ru.office.service.DepartmentService;
import ru.office.util.NoEntryException;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import static ru.office.util.TableNamesEnum.OFFICE;

@Slf4j
@RestController
@RequestMapping("/api/rest/departments")
public class DepartmentController extends BaseController {

    private DepartmentService service;
    private ModelMapper modelMapper;

    public DepartmentController(DepartmentService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<DepartmentDto> getAll(){
        Type type = new TypeToken<List<DepartmentDto>>() {}.getType();
        return modelMapper.map(service.findAll(), type);
    }

    @GetMapping("/{id}")
    public DepartmentDto getOne(@PathVariable UUID id) throws NoEntryException {
        return modelMapper.map(service.findById(id), DepartmentDto.class);
    }

    @PostMapping
    public DepartmentDto create(@RequestBody DepartmentDto dto) {
        DepartmentEntity entity = modelMapper.map(dto, DepartmentEntity.class);
        log.info("Creating {} : {} \n", OFFICE.getName(), dto);
        DepartmentEntity entityNew = service.save(entity);
        return modelMapper.map(entityNew, DepartmentDto.class);
    }

    @PutMapping("/{id}")
    public OfficeDto update(@RequestBody DepartmentDto dto, @PathVariable UUID id) throws NoEntryException {
        DepartmentEntity entity = service.update(dto, id);
        return modelMapper.map(entity, OfficeDto.class);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOfficeCategory(@PathVariable("id") UUID id) throws NoEntryException {
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
