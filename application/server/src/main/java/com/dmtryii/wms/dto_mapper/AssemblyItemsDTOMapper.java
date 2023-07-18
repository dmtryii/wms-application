package com.dmtryii.wms.dto_mapper;

import com.dmtryii.wms.dto.response.AssemblyItemsResponse;
import com.dmtryii.wms.model.Assembly;
import com.dmtryii.wms.model.Item;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AssemblyItemsDTOMapper implements Function<Assembly, AssemblyItemsResponse> {

    @Override
    public AssemblyItemsResponse apply(Assembly assembly) {

        Item item = assembly.getItem();

        return new AssemblyItemsResponse(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getDescription(),
                assembly.getAmount()
        );
    }
}
