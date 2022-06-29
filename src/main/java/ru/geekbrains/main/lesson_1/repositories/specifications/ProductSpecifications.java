package ru.geekbrains.main.lesson_1.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import ru.geekbrains.main.lesson_1.entities.ProductZ;

public class ProductSpecifications {
    private static Specification<ProductZ> priceGreaterOrEqualsThan(int minPrice) {
        return (Specification<ProductZ>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    private static Specification<ProductZ> priceLesserOrEqualsThan(int maxPrice) {
        return (Specification<ProductZ>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    private static Specification<ProductZ> titleLike(String titlePart) {
        return (Specification<ProductZ>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }

    public static Specification<ProductZ> build(MultiValueMap<String, String> params) {
        Specification<ProductZ> spec = Specification.where(null);
        if (params.containsKey("min_price") && !params.getFirst("min_price").isBlank()) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(Integer.parseInt(params.getFirst("min_price"))));
        }
        if (params.containsKey("max_price") && !params.getFirst("max_price").isBlank()) {
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(Integer.parseInt(params.getFirst("max_price"))));
        }
        if (params.containsKey("title") && !params.getFirst("title").isBlank()) {
            spec = spec.and(ProductSpecifications.titleLike(params.getFirst("title")));
        }
        return spec;
    }
}

