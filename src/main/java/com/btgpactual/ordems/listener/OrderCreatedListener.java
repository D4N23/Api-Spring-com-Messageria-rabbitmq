package com.btgpactual.ordems.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.btgpactual.ordems.listener.dto.OrderCreateEventDTO;
import com.btgpactual.ordems.services.OrderService;

import static com.btgpactual.ordems.config.mensageria.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener{

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    private final OrderService orderService;

    public OrderCreatedListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreateEventDTO>message){
        logger.info("Message consumed: {}" , message);

        orderService.save(message.getPayload());
    }
}