package ru.office.controller.rest;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.*;
import ru.office.model.dto.OfficeDto;
import ru.office.model.entity.OfficeEntity;
import ru.office.service.OfficeService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/rest/offices")
public class OfficeController {

    private ModelMapper modelMapper;
    private OfficeService service;

    public OfficeController(ModelMapper modelMapper, OfficeService service) {
        this.modelMapper = modelMapper;
        this.service = service;
    }

    @GetMapping
    public List<OfficeDto> getAll(){
        Type type = new TypeToken<List<OfficeDto>>() {}.getType();
        return modelMapper.map(service.findAll(), type);
    }

    @GetMapping("/{id}")
    public OfficeDto getOne(@PathVariable UUID id) {
        return modelMapper.map(service.findOne(id), OfficeDto.class);
    }

    @PostMapping
    public OfficeDto create(@RequestBody OfficeDto dto) {
        OfficeEntity entity = modelMapper.map(dto, OfficeEntity.class);
        OfficeEntity entityNew = service.save(entity);
        return modelMapper.map(entityNew, OfficeDto.class);
    }

}
