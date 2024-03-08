package com.challenge.bricks.controller.rest;

import com.challenge.bricks.controller.response.CategoryResponse;
import com.challenge.bricks.core.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/challenge/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(path = "/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<CategoryResponse>> getCategoryList()  {
        return ResponseEntity.ok().body(categoryService.getCategoryList());
    }
}
