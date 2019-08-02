package ru.office.model;

import lombok.Data;
import java.util.List;

@Data
public class ReadRequest {
    private Integer page;
    private Integer size;

    public ReadRequest(Integer page, Integer size) {
        this.page = page;
        this.size = size;
        init();
    }

    private void init() {
        if (size == null) {
            size = 20;
        }
    }


    @Override
    public String toString() {
        return "ReadRequest{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}
