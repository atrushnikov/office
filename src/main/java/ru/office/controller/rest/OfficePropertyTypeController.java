package ru.office.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.office.model.dto.OfficePropertyTypeDto;
import ru.office.model.entity.OfficePropertyTypeEntity;
import ru.office.service.OfficePropertyTypeService;
import ru.office.util.NoEntryException;

import java.lang.reflect.Type;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/rest/office_property_types")
public class OfficePropertyTypeController  extends BaseController {

    private OfficePropertyTypeService service;
    private ModelMapper modelMapper;

    public OfficePropertyTypeController(OfficePropertyTypeService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<OfficePropertyTypeDto> getOfficeCategories(){
        Type listType = new TypeToken<List<OfficePropertyTypeDto>>() {}.getType();
        return modelMapper.map(service.findAll(), listType);
    }


    @GetMapping(value = "/{id}")
    public OfficePropertyTypeDto getOfficeCategory(@PathVariable("id") Long id) throws NoEntryException {
        OfficePropertyTypeEntity officeCategoryEntity = service.findById(id);
        return modelMapper.map(officeCategoryEntity, OfficePropertyTypeDto.class);
    }

    @PostMapping
    public OfficePropertyTypeDto createOfficeCategory(@RequestBody OfficePropertyTypeDto dto){
        OfficePropertyTypeEntity entity = modelMapper.map(dto, OfficePropertyTypeEntity.class);
        OfficePropertyTypeEntity entityNew = service.save(entity);
        return modelMapper.map(entityNew, OfficePropertyTypeDto.class);
    }

    @PutMapping("/{id}")
    public OfficePropertyTypeDto updateOfficeCategory(@RequestBody OfficePropertyTypeDto dto, @PathVariable("id") Long id) throws NoEntryException{
        OfficePropertyTypeEntity entity = service.update(dto, id);
        return modelMapper.map(entity, OfficePropertyTypeDto.class);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOfficeCategory(@PathVariable("id") Long id) throws NoEntryException{
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
