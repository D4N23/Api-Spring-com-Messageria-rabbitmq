package com.btgpactual.ordems.listener.dto;

import java.util.List;

public record OrderCreateEventDTO(Long codigoPedido,
                                  Long codigoCliente, 
                                  List<OrderItemEventDTO> itens){

}
