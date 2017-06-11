package by.itacademy.entity;

import by.itacademy.entity.productEntity.Product;
import by.itacademy.entity.userEntity.Client;
import by.itacademy.entity.userEntity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Review extends BaseEntity {
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private Client owner;

    @Column(name = "date")
    private LocalDateTime dateOfCreation;

    @Column(name = "content")
    private String content;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private Product product;
}
