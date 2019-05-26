package ru.office.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.office.model.OfficeCategoryEntity;
import ru.office.service.OfficeCategoryService;

import java.util.List;

@RestController("/api/rest/office-category")
public class OfficeCategoryController {

    private OfficeCategoryService service;

    public OfficeCategoryController(OfficeCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<OfficeCategoryEntity> getOfficeCategories(){
        return service.findAll();
    }

}
