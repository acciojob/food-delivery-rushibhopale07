package com.driver.service.impl;

import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl {

    @Autowired
    OrderRepository orderRepository;

    List<OrderDetailsResponse> orderDetailsResponseList = new ArrayList<>();
    public OrderDetailsResponse getOrder(String id) throws Exception{
        OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();

        OrderEntity orderEntity =orderRepository.findByOrderId(id);
        orderEntity.setStatus(true);

        orderDetailsResponse.setUserId(orderEntity.getUserId());
        orderDetailsResponse.setStatus(true);
        return orderDetailsResponse;
    }


    public OrderDetailsResponse createOrder(OrderDetailsRequestModel order) {
        OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setOrderId(order.getUserId());
        orderEntity.setCost(order.getCost());
        orderEntity.setItems(order.getItems());
        orderEntity.setUserId(order.getUserId());
        orderRepository.save(orderEntity);

        orderDetailsResponse.setOrderId(orderEntity.getOrderId());
        orderDetailsResponse.setCost(orderEntity.getCost());
        orderDetailsResponse.setItems(orderEntity.getItems());
        orderDetailsResponse.setUserId(orderEntity.getUserId());


        orderDetailsResponseList.add(orderDetailsResponse);
        return orderDetailsResponse;
    }


    public OrderDetailsResponse updateOrder( String id, OrderDetailsRequestModel order) throws Exception{

        if(orderRepository.findByOrderId(id) == null)
            throw new Exception("Invalid Order id");

        OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();

        OrderEntity orderEntity = orderRepository.findByOrderId(id);

        orderEntity.setOrderId(order.getUserId());
        orderEntity.setCost(order.getCost());
        orderEntity.setItems(order.getItems());
        orderEntity.setUserId(order.getUserId());

        orderRepository.save(orderEntity);


        orderDetailsResponse.setOrderId(orderEntity.getOrderId());
        orderDetailsResponse.setCost(orderEntity.getCost());
        orderDetailsResponse.setItems(orderEntity.getItems());
        orderDetailsResponse.setUserId(orderEntity.getUserId());


        orderDetailsResponseList.add(orderDetailsResponse);
        return orderDetailsResponse;

    }


    public OperationStatusModel deleteOrder( String id) throws Exception {

        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());

        OrderEntity orderEntity = orderRepository.findByOrderId(id);
        orderRepository.delete(orderEntity);
        orderEntity.setStatus(false);


        operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());
        return operationStatusModel;
    }


    public List<OrderDetailsResponse> getOrders() {

        return orderDetailsResponseList;
    }
}