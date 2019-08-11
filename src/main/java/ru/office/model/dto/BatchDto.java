package ru.office.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class BatchDto<T> {

    private Long totalPages;
    private Long totalElements;
    private Integer pageNumber;
    private Integer batchSize;
    private List<T> data;


    public BatchDto() {
    }

    public BatchDto(Long totalPages, Long totalElements, Integer pageNumber, Integer batchSize, List<T> data) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageNumber = pageNumber;
        this.batchSize = batchSize;
        this.data = data;
    }
}
