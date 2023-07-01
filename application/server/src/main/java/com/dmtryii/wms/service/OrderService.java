package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.OrderDTO;
import com.dmtryii.wms.exception.NotFoundException;
import com.dmtryii.wms.model.Order;
import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    public static final Logger LOG = LoggerFactory.getLogger(OrderLine.class);
    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    public Order createOrder(Long userId, OrderDTO orderDTO) {
        Order order = new Order();
        order.setDataOfOrder(LocalDate.now());
        order.setDetails(orderDTO.getDetails());
        order.setUser(
                userService.getUserById(userId)
        );
        return orderRepository.save(order);
    }

    public Order updateOrder(Long orderId, OrderDTO orderDTO) {
        Order order = getOrderById(orderId);
        order.setDetails(orderDTO.getDetails());
        return orderRepository.save(order);
    }

    public List<Order> getAllOrderByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException("Order not fount by id")
        );
    }

    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
        LOG.info("The order from id {} was deleted", orderId);
    }
}
