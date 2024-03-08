package com.challenge.bricks.core.product.service;


import com.challenge.bricks.controller.response.CategoryResponse;
import com.challenge.bricks.exception.BusinessException;
import com.challenge.bricks.exception.MessageCode;
import com.challenge.bricks.persistence.model.Category;
import com.challenge.bricks.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    @Value(" ${url-api-category}")
    private String urlApiCategory;

    private final CategoryRepository categoryRepository;
    private final RestTemplate restTemplate;
    public Set<CategoryResponse> getCategoryList() {
        var categories = categoryRepository.findAll();
        Set<CategoryResponse> categoriesSet = categories.stream()
                .sorted(Comparator.comparing(Category::getId))
                .map(c -> {
                    return new CategoryResponse().builder()
                            .icon(c.getIcon())
                            .code(c.getCode())
                            .id(c.getId())
                            .description(c.getDescription())
                            .name(c.getName())
                            .build();
                })
                .collect(Collectors.toSet());
        return categoriesSet;
    }

    public void updateCategoryTable() {
        try {
        String urlApi  = this.urlApiCategory.trim();
        HttpEntity request = new HttpEntity(urlApi);
        log.info("Calling api bricks category services with url: " + urlApi);
        long startTime = System.currentTimeMillis();
        ResponseEntity<List<Category>> response= restTemplate.exchange(urlApi, HttpMethod.GET, request, new ParameterizedTypeReference<List<Category>>(){});
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("Time taken: " + urlApi + " - " + timeTaken + " milliseconds.");
        //valido que no exista otro registro con el mismo nombre para evitar repetidos
        response.getBody().stream().forEach(c -> {
            if(categoryRepository.findTop1ByName(c.getName()).isEmpty()){
                categoryRepository.save(c);
            }
        });
        }catch (Exception e){
            throw new BusinessException(MessageCode.ERROR_CALLING_UPDATE_CATEGORY_TABLE);
        }
    }
}
