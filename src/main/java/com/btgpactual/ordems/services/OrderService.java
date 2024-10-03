package com.btgpactual.ordems.services;

import java.math.BigDecimal;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.btgpactual.ordems.controller.dto.OrderResponseDTO;
import com.btgpactual.ordems.entitys.OrderEntity;
import com.btgpactual.ordems.listener.dto.OrderCreateEventDTO;
import com.btgpactual.ordems.repositorys.OrderRepository;
import com.btgpactual.ordems.services.servicemappers.OrderMapper;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void save(OrderCreateEventDTO orderCreateEventDTO) {
        OrderEntity entity = OrderMapper.toEntity(orderCreateEventDTO);
        orderRepository.save(entity);
    }

    public Page<OrderResponseDTO> findAllByCustomerId(Long customerId, PageRequest pageRequest) {
        var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);
        return orders.map(OrderResponseDTO::fromEntity);
    }

    public BigDecimal findTotalOrdersByCustomerId(Long customerId) {
        var aggregations = newAggregation(
                match(Criteria.where("customerId").is(customerId)),
                group().sum("total").as("total"));

        var response = mongoTemplate.aggregate(aggregations, "tb_orders", Document.class);

        // return (BigDecimal) response.getUniqueMappedResult().getOrDefault("total", BigDecimal.ZERO);
        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
    }

}
