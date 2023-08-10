package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.OrderLineRequest;
import com.dmtryii.wms.dto.request.OrderRequest;
import com.dmtryii.wms.exception.ResourceNotFoundException;
import com.dmtryii.wms.model.Order;
import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.model.Product;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.model.enums.EState;
import com.dmtryii.wms.repository.OrderLineRepository;
import com.dmtryii.wms.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    public static final Logger LOG = LoggerFactory.getLogger(Order.class);
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final ProductService productService;
    private final UserService userService;

    public Order createOrder(OrderRequest request, Principal principal) {
        User user = userService.getUserByPrincipal(principal);

        Order order = Order.builder()
                .dataOfOrder(LocalDateTime.now())
                .state(EState.WAITING_FOR_CONFIRMATION)
                .details(request.getDetails())
                .user(user)
                .build();
        orderRepository.save(order);
        LOG.info("Order by id {} was created", order.getId());
        return order;
    }

    public Order updateOrder(Long orderId, OrderRequest request) {
        Order order = getOrderById(orderId);
        order.setDetails(request.getDetails());
        orderRepository.save(order);
        return order;
    }

    public OrderLine addProductToOrder(OrderLineRequest request) {
        Order order = getOrderById(request.getOrderId());
        Product product = productService.getProductById(request.getProductId());
        int amount = request.getAmount();

        OrderLine _orderLine = orderLineRepository
                .findOrderLineByOrderAndProduct(order, product);

        if(_orderLine != null) {
            _orderLine.setAmount(
                    _orderLine.getAmount() + amount
            );
            orderLineRepository.save(_orderLine);
            return _orderLine;
        }

        OrderLine orderLine = new OrderLine(order, product, amount);

        orderLineRepository.save(orderLine);
        return orderLine;
    }

    public List<Order> getAllOrderByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("The order not fount by id: " + orderId)
        );
    }

    public void deleteOrderById(Long orderId) {
        Order order = getOrderById(orderId);
        orderRepository.delete(order);
        LOG.info("Order by id {} was deleted", orderId);
    }
}
