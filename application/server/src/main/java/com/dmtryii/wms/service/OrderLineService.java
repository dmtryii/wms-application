package com.dmtryii.wms.service;


import com.dmtryii.wms.exception.ResourceNotFoundException;
import com.dmtryii.wms.model.Order;
import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.model.Product;
import com.dmtryii.wms.repository.OrderLineRepository;
import com.dmtryii.wms.repository.OrderRepository;
import com.dmtryii.wms.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineService {
    public static final Logger LOG = LoggerFactory.getLogger(OrderLine.class);

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderLineRepository orderLineRepository;

    @Autowired
    public OrderLineService(OrderRepository orderRepository,
                            ProductRepository productRepository,
                            OrderLineRepository orderLineRepository)
    {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderLineRepository = orderLineRepository;
    }

    public OrderLine addProductToOrder(Long orderId, Long productId, int amount) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(""));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(""));

        OrderLine orderLine = new OrderLine(
                order,
                product,
                amount
        );

        LOG.info("Added product - {} to order - {},", productId, orderId);
        return orderLineRepository.save(orderLine);
    }

}
