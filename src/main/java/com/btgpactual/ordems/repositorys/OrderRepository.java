package com.btgpactual.ordems.repositorys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.btgpactual.ordems.controller.dto.OrderResponseDTO;
import com.btgpactual.ordems.entitys.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity,Long> {

    Page<OrderEntity> findAllByCustomerId(Long customerId, PageRequest pageRequest);
    
}
