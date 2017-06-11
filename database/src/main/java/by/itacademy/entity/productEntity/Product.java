package by.itacademy.entity.productEntity;

import by.itacademy.entity.BaseEntity;
import by.itacademy.entity.Review;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class Product extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "mark")
    private Double mark;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private Set<Review> reviews = new HashSet<>();

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private Set<Characteristic> characteristics = new HashSet<>();
}
