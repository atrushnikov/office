package ru.office.model;

import lombok.Data;

@Data
public class ReadRequest {
    private Integer pageNumber;
    private Integer batchSize;

    public ReadRequest(Integer pageNumber, Integer batchSize) {
        this.pageNumber = pageNumber;
        this.batchSize = batchSize;
        init();
    }

    private void init() {
        if (batchSize == null) {
            batchSize = 20;
        }
    }


    @Override
    public String toString() {
        return "ReadRequest{" +
                "pageNumber=" + pageNumber +
                ", batchSize=" + batchSize +
                '}';
    }
}
