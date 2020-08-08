package com.iyzico.challenge.mapper;

import com.iyzico.challenge.dto.ProductRequest;
import com.iyzico.challenge.dto.ProductResponse;
import com.iyzico.challenge.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
public interface ProductMapper {
    ProductMapper map = Mappers.getMapper(ProductMapper.class);

    Product productRequestToProduct(ProductRequest productRequest);

    ProductResponse productToProductResponse(Product product);

    List<ProductResponse> productListToProductResponseList(List<Product> products);
}
