package ru.geekbrains.main.lesson_1.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.main.lesson_1.entities.ProductZ;

@Data
@NoArgsConstructor
public class ProductDtoZ {
    private Long id;
    private String title;
    private int price;
    private String description;
    private String pathname;

    public ProductDtoZ(ProductZ p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
        this.description = p.getDescription ();
        this.pathname = p.getPathname ();
    }
}
