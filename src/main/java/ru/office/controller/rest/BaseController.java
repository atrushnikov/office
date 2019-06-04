package ru.office.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.office.model.dto.ResponseDto;
import ru.office.util.NoEntryException;

import java.util.HashMap;

@Slf4j
public class BaseController {

    @ExceptionHandler(NoEntryException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseDto handleNoContentException(NoEntryException e){
        HashMap valueMap = new HashMap();
        valueMap.put("id", e.getId());
        valueMap.put("tableName", e.getTableName());
        StringSubstitutor stringSubstitutor = new StringSubstitutor(valueMap);
        String message = stringSubstitutor.replace(e.getMessage());
        log.info(message);
        return new ResponseDto(stringSubstitutor.replace(e.getMessage()));
    }

}
