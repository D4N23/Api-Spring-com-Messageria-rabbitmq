package com.btgpactual.ordems.services.servicemappers;

import java.math.BigDecimal;
import java.util.List;

import com.btgpactual.ordems.entitys.OrderEntity;
import com.btgpactual.ordems.entitys.OrderItem;
import com.btgpactual.ordems.listener.dto.OrderCreateEventDTO;

public class OrderMapper {

    public static OrderEntity toEntity(OrderCreateEventDTO orderCreateEventDTO){
        OrderEntity entity = new OrderEntity();
        entity.setOrderId(orderCreateEventDTO.codigoPedido());
        entity.setCustomerId(orderCreateEventDTO.codigoCliente());
        entity.setItens(getOrderItens(orderCreateEventDTO));
        entity.setTotal(getTotal(orderCreateEventDTO));
        return entity;
    }

      private static BigDecimal getTotal(OrderCreateEventDTO orderCreateEventDTO) {
        return orderCreateEventDTO.itens()
                .stream()
                .map(
                        item -> item.preco().multiply(BigDecimal.valueOf(item.quantidade())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private static List<OrderItem> getOrderItens(OrderCreateEventDTO orderCreateEventDTO) {
        return orderCreateEventDTO.itens().stream().map(
                item -> new OrderItem(item.produto(), item.quantidade(), item.preco())).toList();
    }
}
