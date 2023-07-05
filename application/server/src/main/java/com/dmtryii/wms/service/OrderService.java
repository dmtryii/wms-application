package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.OrderDTO;
import com.dmtryii.wms.dto.request.OrderRequest;
import com.dmtryii.wms.dto_mapper.OrderDTOMapper;
import com.dmtryii.wms.exception.NotFoundException;
import com.dmtryii.wms.model.Order;
import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    public static final Logger LOG = LoggerFactory.getLogger(OrderLine.class);
    private final OrderDTOMapper orderDTOMapper;
    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderDTO createOrder(OrderRequest orderRequest) {
        User user = userService.getUserByUsername(orderRequest.username());

        Order order = new Order();
        order.setDataOfOrder(LocalDate.now());
        order.setDetails(orderRequest.details());
        order.setUser(user);
        orderRepository.save(order);

        LOG.info("Order by id {} was created", order.getId());
        return orderDTOMapper.apply(order);
    }

    public OrderDTO updateOrder(Long orderId, OrderRequest orderRequest) {

        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException("")
        );

        order.setDetails(orderRequest.details());
        orderRepository.save(order);

        return orderDTOMapper.apply(order);
    }

    public List<OrderDTO> getAllOrderByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId)
                .stream()
                .map(orderDTOMapper)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrder() {
        return orderRepository.findAll()
                .stream()
                .map(orderDTOMapper)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException("Order not fount by id: " + orderId)
        );
        return orderDTOMapper.apply(order);
    }

    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
        LOG.info("Order by id {} was deleted", orderId);
    }
}
