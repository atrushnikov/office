package ru.office.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/rest")
public class MainController {

    private  BuildProperties buildProperties;

    public MainController(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @GetMapping("/version")
    public String getBuildVersion() {
        return "\nName : " + buildProperties.getName() + "\nVersion: " + buildProperties.getVersion() + "\nBuild time : " + buildProperties.getTime();
    }

}
