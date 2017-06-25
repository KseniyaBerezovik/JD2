package by.itacademy.entity.productEntity;

import by.itacademy.entity.otherEntity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "characteristics")
@Data
public class Characteristic extends BaseEntity {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "detail_id")
    private Detail detail;

    @Column(name = "value")
    private String value;
}
