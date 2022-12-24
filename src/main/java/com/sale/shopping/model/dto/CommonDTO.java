package com.sale.shopping.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonDTO<T>{

    private int statusCode;
    private T data;


}
