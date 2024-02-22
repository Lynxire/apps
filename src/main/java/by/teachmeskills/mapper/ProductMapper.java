package by.teachmeskills.mapper;

import by.teachmeskills.api.orders.ProductRequest;
import by.teachmeskills.api.orders.ProductResponse;
import by.teachmeskills.entity.Product;

public class ProductMapper {
    public Product toEntity(ProductRequest productRequest){
        Product product = new Product();
        product.setId(productRequest.getId());
        product.setSum(productRequest.getSum());
        product.setName(productRequest.getName());
        product.setCode(productRequest.getCode());
        product.setQuantity(productRequest.getQuantity());
        return product;
    }

    public ProductResponse toResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setCode(product.getCode());
        productResponse.setSum(product.getSum());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setName(product.getName());
        return productResponse;
    }


}
