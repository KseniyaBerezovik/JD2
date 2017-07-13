package by.itacademy.entity.orderEntity;

import by.itacademy.entity.otherEntity.BaseEntity;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.entity.userEntity.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
public class Cart extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "amount")
    private Integer amount;
}