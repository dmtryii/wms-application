package com.dmtryii.wms.dto_mapper;

import com.dmtryii.wms.dto.OrderLineDTO;
import com.dmtryii.wms.model.OrderLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class OrderLineDTOMapper implements Function<OrderLine, OrderLineDTO> {
    private final OrderDTOMapper orderDTOMapper;

    @Override
    public OrderLineDTO apply(OrderLine orderLine) {
        return new OrderLineDTO(
                orderDTOMapper.apply(orderLine.getOrder()),
                orderLine.getProduct(),
                orderLine.getAmount()
        );
    }
}
