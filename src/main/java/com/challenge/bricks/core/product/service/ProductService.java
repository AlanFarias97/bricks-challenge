package com.challenge.bricks.core.product.service;

import com.challenge.bricks.controller.request.ProductCreateRequest;
import com.challenge.bricks.controller.request.ProductUpdateRequest;
import com.challenge.bricks.controller.response.ProductListResponse;
import com.challenge.bricks.controller.response.ProductResponse;
import com.challenge.bricks.exception.BusinessException;
import com.challenge.bricks.exception.MessageCode;
import com.challenge.bricks.persistence.criteria.ProductSpecification;
import com.challenge.bricks.persistence.model.Category;
import com.challenge.bricks.persistence.model.Product;
import com.challenge.bricks.persistence.repository.CategoryRepository;
import com.challenge.bricks.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    ModelMapper mapper = new ModelMapper();
    public Map<String, Object> getProductList(ProductSpecification.ProductCriteria productRequest, Pageable paging) {
        Page<Product> result = productRepository.findAll(ProductSpecification.findProductByCriteria(productRequest), paging);
        Map<String, Object> response = new HashMap<>();
        response.put("data", result.getContent().stream().map(ProductListResponse::convertTo).collect(Collectors.toList()).stream().filter(pjr -> pjr.getActive().equals(true)));
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());
        return response;
    }

    public Long createProduct(ProductCreateRequest productCreateRequest) {
        Product newProduct = new Product();
        newProduct.setActive(productCreateRequest.getActive());
        newProduct.setName(productCreateRequest.getName());
        newProduct.setStock(productCreateRequest.getStock());
        newProduct.setPrice(productCreateRequest.getPrice());
        //si el category id que se pasa en el request es invalido o no existe se arroja error
        Category category = categoryRepository.findById(productCreateRequest.getCategoryId()).orElseThrow( ()->  new BusinessException(MessageCode.CATEGORY_NOT_FOUND));
        newProduct.setCategory(category);
        productRepository.save(newProduct);
        return newProduct.getProductId();

    }

    public ProductResponse findProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new BusinessException(MessageCode.PRODUCT_NOT_FOUND));
        var productDTO = mapper.map(product, ProductResponse.class);
        productDTO.setCategory(ProductResponse.CategoryDTO.builder()
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .build());
        return productDTO;
    }

    public void deleteById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new BusinessException(MessageCode.PRODUCT_NOT_FOUND));
        product.setActive(false);
        productRepository.save(product);
    }

    public void updateProduct(Long productId, ProductUpdateRequest request) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new BusinessException(MessageCode.PRODUCT_NOT_FOUND));
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow( ()->  new BusinessException(MessageCode.CATEGORY_NOT_FOUND));
        product.setCategory(category);
        product.setName(request.getName());
        product.setActive(request.getActive());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());
        productRepository.save(product);
    }
}
