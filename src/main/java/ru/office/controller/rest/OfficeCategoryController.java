package ru.office.controller.rest;

import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.office.model.dto.OfficeCategoryDto;
import ru.office.model.entity.OfficeCategoryEntity;
import ru.office.service.OfficeCategoryService;
import ru.office.util.NoEntryException;

import java.lang.reflect.Type;
import java.util.List;

@Api(tags = "Office Category")
@RestController
@RequestMapping("/api/rest/office-categories")
public class OfficeCategoryController {

    private OfficeCategoryService service;
    private ModelMapper modelMapper;

    public OfficeCategoryController(OfficeCategoryService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<OfficeCategoryDto> getOfficeCategories(){
        Type listType = new TypeToken<List<OfficeCategoryDto>>() {}.getType();
        return modelMapper.map(service.findAll(), listType);
    }


    @GetMapping(value = "/{id}")
    public OfficeCategoryDto getOfficeCategory(@PathVariable("id") Long id) throws NoEntryException {
        OfficeCategoryEntity officeCategoryEntity = service.findById(id);
        return modelMapper.map(officeCategoryEntity, OfficeCategoryDto.class);
    }

    @PostMapping
    public OfficeCategoryDto createOfficeCategory(@RequestBody OfficeCategoryDto dto){
        OfficeCategoryEntity entity = modelMapper.map(dto, OfficeCategoryEntity.class);
        OfficeCategoryEntity entityNew = service.save(entity);
        return modelMapper.map(entityNew, OfficeCategoryDto.class);
    }

    @PutMapping("/{id}")
    public OfficeCategoryDto updateOfficeCategory(@RequestBody OfficeCategoryDto dto, @PathVariable("id") Long id) throws NoEntryException{
        OfficeCategoryEntity entity = service.update(dto, id);
        return modelMapper.map(entity, OfficeCategoryDto.class);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOfficeCategory(@PathVariable("id") Long id) throws NoEntryException{
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
