package com.btgpactual.ordems.controller.dto;

import java.math.BigDecimal;

import org.bson.codecs.BigDecimalCodec;

import com.btgpactual.ordems.entitys.OrderEntity;

public record OrderResponseDTO(
    Long ordeId,
    Long customerID,
    BigDecimal total
) {

    public static OrderResponseDTO fromEntity(OrderEntity orderEntity){
        return new OrderResponseDTO(orderEntity.getOrderId(), orderEntity.getCustomerId(), orderEntity.getTotal());
    }
    
}
