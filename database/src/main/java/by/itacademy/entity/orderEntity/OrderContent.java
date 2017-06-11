package by.itacademy.entity.orderEntity;

import by.itacademy.entity.BaseEntity;
import by.itacademy.entity.productEntity.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_content")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class OrderContent extends BaseEntity {

    @OneToOne(cascade = CascadeType.PERSIST)
    @PrimaryKeyJoinColumn
    private Product product;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @PrimaryKeyJoinColumn(name = "order_id")
    private Order order;
}
