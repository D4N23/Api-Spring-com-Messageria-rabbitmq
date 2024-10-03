package com.btgpactual.ordems.controller.dto;

import java.util.List;
import java.util.Map;

public record ApiResponseDTO<T>(
        Map<String, Object> sumary,
        List<T> data,
        PaginationResponse pagination) {

}
