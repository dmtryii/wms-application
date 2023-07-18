package com.dmtryii.wms.dto_mapper;

import com.dmtryii.wms.dto.response.AssemblyItemsResponse;
import com.dmtryii.wms.dto.response.AssemblyResponse;
import com.dmtryii.wms.model.Assembly;
import com.dmtryii.wms.service.AssemblyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AssemblyDTOMapper implements Function<Assembly, AssemblyResponse> {
    private final AssemblyService assemblyService;
    private final AssemblyItemsDTOMapper assemblyItemsDTOMapper;

    @Override
    public AssemblyResponse apply(Assembly assembly) {

        List<Assembly> assemblies = assemblyService
                .getAssemblyByProductId(assembly.getProduct().getId());

        List<AssemblyItemsResponse> items = assemblies
                .stream()
                .map(assemblyItemsDTOMapper).toList();

        return new AssemblyResponse(
                assembly.getProduct(),
                items
        );
    }
}
