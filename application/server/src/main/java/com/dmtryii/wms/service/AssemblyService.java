package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.AssemblyRequest;
import com.dmtryii.wms.model.Assembly;
import com.dmtryii.wms.model.Item;
import com.dmtryii.wms.repository.AssemblyRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssemblyService {
    public static final Logger LOG = LoggerFactory.getLogger(Assembly.class);
    private final AssemblyRepository assemblyRepository;
    private final ProductService productService;
    private final ItemService itemService;

    public Assembly addAssemblyInstructions(AssemblyRequest assemblyRequest) {

        Long productId = assemblyRequest.productId();
        Long itemId = assemblyRequest.itemId();
        int amount = assemblyRequest.amount();

        if(amount <= 0) throw new RuntimeException("An assembly cannot contain less than one component");

        if(itemAndProductsRelated(productId, itemId)) {
            return assemblyRepository
                    .findAssembliesByProductIdAndItemId(productId, itemId);
        }

        Assembly assembly = new Assembly(
                productService.getProductById(productId),
                itemService.getItemById(itemId),
                amount
        );
        return assemblyRepository.save(assembly);
    }

    @Transactional
    public void deleteAssemblyInstructionsByProductId(Long productId) {
        assemblyRepository.deleteAllAssemblyByProductId(productId);
        LOG.info("An assembly with product ID {} has been delete", productId);
    }

    @Transactional
    public void deleteAssemblyInstructions(Long productId, Long itemId) {
        if (!itemAndProductsRelated(productId, itemId)) {
            return;
        }
        assemblyRepository.deleteAssemblyByProductIdAndItemId(productId, itemId);
        LOG.info("An assembly with product ID {} and item ID {} has been delete", productId, itemId);
    }

    public List<Item> getProductAssembly(Long productId) {
        return assemblyRepository
                .findAssembliesByProductId(productId)
                .stream()
                .map(Assembly::getItem).toList();
    }

    public List<Assembly> getAssemblyByProductId(Long productId) {
        return assemblyRepository
                .findAssembliesByProductId(productId);
    }

    private boolean itemAndProductsRelated(Long productId, Long itemId) {
        Assembly assembly = assemblyRepository
                .findAssembliesByProductIdAndItemId(productId, itemId);

        return assembly != null;
    }
}
