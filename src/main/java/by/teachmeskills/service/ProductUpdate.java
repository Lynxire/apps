package by.teachmeskills.service;

import by.teachmeskills.api.orders.ProductRequest;
import by.teachmeskills.api.orders.ProductResponse;
import by.teachmeskills.entity.Product;
import by.teachmeskills.mapper.ProductMapper;
import by.teachmeskills.repository.OrderInterfaceRepository;
import by.teachmeskills.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.util.Collection;

public class ProductUpdate {

    public ProductResponse search(Long id)
    {
        OrderInterfaceRepository repository = new ProductRepository();
        Product repositoryID = repository.findID(Long.valueOf(id));
        if(repositoryID == null || repositoryID.equals(0)){
            throw new RuntimeException("Неверное ID");
        }
        ProductMapper productMapper = new ProductMapper();
        return productMapper.toResponse(repositoryID);

    }
    public ProductResponse add(ProductRequest productRequest){
        ProductMapper productMapper = new ProductMapper();
        Product product = productMapper.toEntity(productRequest);
        OrderInterfaceRepository orderInterfaceRepository = new ProductRepository();
        orderInterfaceRepository.add(product);
        return productMapper.toResponse(product);
    }

    public void deleteById(Long id){
        OrderInterfaceRepository repository = new ProductRepository();
        repository.deleteById(id);
    }

    public Collection<ProductResponse> all(){
        OrderInterfaceRepository repository = new ProductRepository();
        ProductMapper productMapper=new ProductMapper();
        Collection<Product> products = repository.allProduct();
        Collection<ProductResponse>  productResponses=products.stream()
                .map(product ->productMapper.toResponse(product)).
                toList();
        return productResponses;
    }


}
