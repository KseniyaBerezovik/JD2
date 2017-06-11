package by.itacademy.entity.orderEntity;

import by.itacademy.entity.BaseEntity;
import by.itacademy.entity.userEntity.Client;
import by.itacademy.entity.userEntity.Employee;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode (exclude = "id")
public class Order extends BaseEntity {
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner_id")
    private Client owner;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "status")
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Set<OrderContent> content = new HashSet<>();

    @OneToOne(cascade = CascadeType.PERSIST)
    @PrimaryKeyJoinColumn(name = "detail_id")
    private OrderDetail detail;
}
