package by.teachmeskills.service;

import by.teachmeskills.api.products.ProductRequest;
import by.teachmeskills.api.products.ProductResponse;
import by.teachmeskills.entity.Product;
import by.teachmeskills.mapper.ProductMapper;
import by.teachmeskills.repository.ProductInterfaceRepository;
import by.teachmeskills.repository.impl.product.ProductJdbcRepository;
import by.teachmeskills.repository.impl.product.ProductRepository;

import java.util.Collection;

public class ProductUpdate {

    public ProductResponse search(Long id) {
        ProductInterfaceRepository repository = new ProductJdbcRepository();
        Product repositoryID = repository.findByID(Long.valueOf(id));
        if (repositoryID == null || repositoryID.equals(0)) {
            throw new RuntimeException("Неверное ID");
        }
        ProductMapper productMapper = new ProductMapper();
        return productMapper.toResponse(repositoryID);

    }

    public ProductResponse add(ProductRequest productRequest) {
        ProductMapper productMapper = new ProductMapper();
        Product product = productMapper.toEntity(productRequest);
        ProductInterfaceRepository productInterfaceRepository = new ProductJdbcRepository();
        productInterfaceRepository.add(product);
        return productMapper.toResponse(product);
    }

    public void deleteById(Long id) {
        ProductInterfaceRepository repository = new ProductJdbcRepository();
        repository.deleteById(id);
    }

    public Collection<ProductResponse> all() {
        ProductInterfaceRepository repository = new ProductJdbcRepository();
        ProductMapper productMapper = new ProductMapper();
        Collection<Product> products = repository.allProduct();
        Collection<ProductResponse> productResponses = products.stream().map(product -> productMapper.toResponse(product))
                        .toList();
        return productResponses;
    }


}
