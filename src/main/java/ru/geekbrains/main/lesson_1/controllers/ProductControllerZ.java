package ru.geekbrains.main.lesson_1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.main.lesson_1.dto.ProductDtoZ;
import ru.geekbrains.main.lesson_1.entities.ProductZ;
import ru.geekbrains.main.lesson_1.exceptions.ResourceNotFoundException;
import ru.geekbrains.main.lesson_1.repositories.specifications.ProductSpecifications;
import ru.geekbrains.main.lesson_1.services.ProductServiceZ;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductControllerZ {
    private final ProductServiceZ productService;

    @GetMapping
    public Page<ProductDtoZ> findAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }

        return productService.findAll(ProductSpecifications.build(params), page, 4);
    }

    @GetMapping("/{id}")
    public ProductDtoZ findProductById(@PathVariable Long id) {
        return productService.findProductDtoById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doens't exist"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductZ saveNewProduct(@RequestBody ProductZ product) {
        product.setId(null);
        return productService.saveOrUpdate(product);
    }

    @PutMapping
    public ProductZ updateProduct(@RequestBody ProductZ product) {
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void updateProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}