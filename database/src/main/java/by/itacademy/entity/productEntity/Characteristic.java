package by.itacademy.entity.productEntity;

import by.itacademy.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "characteristics")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Characteristic extends BaseEntity {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "criterion")
    private String criterion;

    @Column(name = "value")
    private String value;
}
