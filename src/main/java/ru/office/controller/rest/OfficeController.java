package ru.office.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.office.model.ReadRequest;
import ru.office.model.dto.OfficeDto;
import ru.office.model.entity.OfficeEntity;
import ru.office.service.OfficeService;
import ru.office.util.NoEntryException;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import static ru.office.util.TableNamesEnum.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/rest/offices")
public class OfficeController extends BaseController {

    private ModelMapper modelMapper;
    private OfficeService service;

    public OfficeController(ModelMapper modelMapper, OfficeService service) {
        this.modelMapper = modelMapper;
        this.service = service;
    }

    @GetMapping
    public List<OfficeDto> getAll(@RequestParam(value = "page") Integer page,
                                  @RequestParam(value = "size", required = false) Integer size){
        Type type = new TypeToken<List<OfficeDto>>() {}.getType();
        return modelMapper.map(service.findAll(new ReadRequest(page, size)), type);
    }

    @GetMapping("/{id}")
    public OfficeDto getOne(@PathVariable UUID id) throws NoEntryException {
        return modelMapper.map(service.findById(id), OfficeDto.class);
    }

    @PostMapping
    public OfficeDto create(@RequestBody OfficeDto dto) {
        OfficeEntity entity = modelMapper.map(dto, OfficeEntity.class);
        log.info("Creating {} : {} \n", OFFICE.getName(), dto);
        OfficeEntity entityNew = service.save(entity);
        return modelMapper.map(entityNew, OfficeDto.class);
    }

    @PutMapping("/{id}")
    public OfficeDto update(@RequestBody OfficeDto dto, @PathVariable UUID id) throws NoEntryException {
        OfficeEntity entity = service.update(dto, id);
        return modelMapper.map(entity, OfficeDto.class);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOfficeCategory(@PathVariable("id") UUID id) throws NoEntryException{
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
