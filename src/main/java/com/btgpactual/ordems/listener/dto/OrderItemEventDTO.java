package com.btgpactual.ordems.listener.dto;

import java.math.BigDecimal;

public record OrderItemEventDTO(
    String produto,
    Integer quantidade,
    BigDecimal preco
) {

}
