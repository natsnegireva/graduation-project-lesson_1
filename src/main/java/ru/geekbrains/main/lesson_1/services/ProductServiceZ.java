package ru.geekbrains.main.lesson_1.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.main.lesson_1.dto.ProductDtoZ;
import ru.geekbrains.main.lesson_1.entities.ProductZ;
import ru.geekbrains.main.lesson_1.repositories.ProductRepositoryZ;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceZ {
    private final ProductRepositoryZ productRepository;

    public Optional<ProductZ> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<ProductDtoZ> findProductDtoById(Long id) {
        return productRepository.findById(id).map(ProductDtoZ::new);
    }

    public Page<ProductDtoZ> findAll(Specification<ProductZ> spec, int page, int pageSize) {
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDtoZ::new);
    }

    public ProductZ saveOrUpdate(ProductZ product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
