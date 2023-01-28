package com.sale.shopping.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonDTO<T>{
    private int statusCode;
    private T data;
    public CommonDTO(int statusCode) {
        super();
        this.statusCode = statusCode;
    }
    public CommonDTO(int statusCode, T data) {
        super();
        this.statusCode = statusCode;
        this.data = data;
    }
}
