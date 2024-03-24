package by.teachmeskills.service;

import by.teachmeskills.api.products.ProductRequest;
import by.teachmeskills.api.products.ProductResponse;
import by.teachmeskills.entity.Product;
import by.teachmeskills.mapper.ProductMapper;
import by.teachmeskills.repository.ProductInterfaceRepository;
import by.teachmeskills.repository.impl.product.ProductJdbcRepository;

import java.util.Collection;
import java.util.List;

public class ProductService {

    public ProductResponse search(Long id) {
        ProductInterfaceRepository repository = new ProductJdbcRepository();
        Product product = repository.findByID(id);
        if (product == null) {
            throw new RuntimeException("Неверное ID");
        }
        ProductMapper productMapper = new ProductMapper();
        return productMapper.toResponse(product);

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

    public List<ProductResponse>getProductsByIds(List<Long> ids){
        ProductInterfaceRepository repository = new ProductJdbcRepository();
        List<Product> products=repository.getProductsByIds(ids);
        if(products.isEmpty()){
            throw new RuntimeException("Товары не добавлены");
        }
        ProductMapper productMapper = new ProductMapper();
        List<ProductResponse> productResponses = products.stream().map(product -> productMapper.toResponse(product))
                .toList();
        return productResponses;
    }


}
