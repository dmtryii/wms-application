package com.dmtryii.wms.dto_mapper;

import com.dmtryii.wms.dto.OrderDTO;
import com.dmtryii.wms.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class OrderDTOMapper implements Function<Order, OrderDTO> {
    private final UserDTOMapper userDTOMapper;

    @Override
    public OrderDTO apply(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getDataOfOrder(),
                order.getDetails(),
                userDTOMapper.apply(order.getUser())
        );
    }
}
