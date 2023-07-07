package com.dmtryii.wms.dto_mapper;

import com.dmtryii.wms.dto.SupplierDTO;
import com.dmtryii.wms.model.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SupplierDTOMapper implements Function<Supplier, SupplierDTO> {
    private final UserDTOMapper userDTOMapper;
    @Override
    public SupplierDTO apply(Supplier supplier) {
        return new SupplierDTO(
                supplier.getId(),
                supplier.getCompanyName(),
                supplier.getRating(),
                userDTOMapper.apply(supplier.getUser())
        );
    }
}
