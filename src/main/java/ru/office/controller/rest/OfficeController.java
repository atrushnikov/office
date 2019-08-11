package ru.office.controller.rest;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.office.model.ReadRequest;
import ru.office.model.dto.BatchDto;
import ru.office.model.dto.OfficeDto;
import ru.office.model.dto.ResponseDto;
import ru.office.model.entity.OfficeEntity;
import ru.office.service.OfficeService;
import ru.office.util.NoEntryException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static ru.office.util.TableNamesEnum.*;

@Api(tags = "Office Commands")
@Slf4j
@RestController
@RequestMapping(path = "/api/rest/offices")
public class OfficeController {

    private ModelMapper modelMapper;
    private OfficeService service;

    public OfficeController(ModelMapper modelMapper, OfficeService service) {
        this.modelMapper = modelMapper;
        this.service = service;
    }


    @ApiOperation(value = "View a list of available offices")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping
    public BatchDto<OfficeDto> getAll(@ApiParam(value = "Page number starts at 1", required = true) @RequestParam(value = "pageNumber") Integer page,
                                      @RequestParam(value = "batchSize", required = false) Integer size){
        return service.findAll(new ReadRequest(page, size));
    }

    @ApiOperation(value = "Get an office by Id")
    @GetMapping("/{id}")
    public OfficeDto getOne(@PathVariable UUID id) throws NoEntryException {
        return modelMapper.map(service.findById(id), OfficeDto.class);
    }

    @ApiOperation(value = "Add an office")
    @PostMapping
    public OfficeDto create(@ApiParam(value = "Office object store in database table", required = true) @RequestBody OfficeDto dto) {
        OfficeEntity entity = modelMapper.map(dto, OfficeEntity.class);
        log.info("Creating {} : {} \n", OFFICE.getName(), dto);
        OfficeEntity entityNew = service.save(entity);
        return modelMapper.map(entityNew, OfficeDto.class);
    }

    @ApiOperation(value = "Update an office")
    @PutMapping("/{id}")
    public OfficeDto update(@RequestBody OfficeDto dto, @PathVariable UUID id) throws NoEntryException {
        OfficeEntity entity = service.update(dto, id);
        return modelMapper.map(entity, OfficeDto.class);
    }

    @ApiOperation(value = "Delete an office")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteOfficeCategory(@PathVariable("id") UUID id) throws NoEntryException{
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

}
