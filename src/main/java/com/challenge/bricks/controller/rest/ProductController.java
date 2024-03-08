package com.challenge.bricks.controller.rest;

import com.challenge.bricks.controller.request.ProductCreateRequest;
import com.challenge.bricks.controller.request.ProductUpdateRequest;
import com.challenge.bricks.controller.response.ProductResponse;
import com.challenge.bricks.core.product.service.ProductService;
import com.challenge.bricks.persistence.criteria.ProductSpecification;
import com.challenge.bricks.util.JsonUtils;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/challenge/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> getProductList(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(defaultValue = "-productId") String sort,
            @RequestParam(defaultValue = "") String filter)  {
        ProductSpecification.ProductCriteria productRequest = filter.isEmpty() ? null : (ProductSpecification.ProductCriteria) JsonUtils.base64ToObjectJson(filter, ProductSpecification.ProductCriteria.class.getName());
        Pageable paging = PageRequest.of(offset, limit, Sort.by(sort.charAt(0) == '-' ? Sort.Direction.DESC : Sort.Direction.DESC, sort.replace("-", "")));
        return ResponseEntity.ok().body(productService.getProductList(productRequest, paging));
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, Object>> createProduct(@Valid @RequestBody ProductCreateRequest productCreateRequest) {
        Long productId = productService.createProduct(productCreateRequest);
        return ResponseEntity.ok().body(Map.of("productId", productId));
    }

    @GetMapping(path = "/find/{productId}")
    public ResponseEntity<ProductResponse> findById(@NotNull @PathVariable("productId") Long productId) {
        return ResponseEntity.ok().body(productService.findProductById(productId));
    }

    @DeleteMapping(path = "/delete/{productId}")
    public void deleteProductById (@NotNull @PathVariable("productId") Long productId){
        productService.deleteById(productId);
    }

    @PutMapping("/update/{productId}")
    public void updateProduct(@Valid @RequestBody ProductUpdateRequest request, @PathVariable Long productId) {
        productService.updateProduct(productId, request);
    }
}
