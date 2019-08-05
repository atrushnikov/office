package ru.office.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.office.model.dto.ResponseDto;
import ru.office.util.NoEntryException;

import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoEntryException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseDto> handleNoContentException(NoEntryException e){
        Map valueMap = Map.ofEntries(Map.entry("id", e.getId()), Map.entry("tableName", e.getTableName()));
        StringSubstitutor stringSubstitutor = new StringSubstitutor(valueMap);
        String message = stringSubstitutor.replace(e.getMessage());
        log.info(message);
        return new ResponseEntity<>(new ResponseDto(message), HttpStatus.NOT_FOUND);
    }

}
