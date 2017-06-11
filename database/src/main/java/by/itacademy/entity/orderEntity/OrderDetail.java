package by.itacademy.entity.orderEntity;

import by.itacademy.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class OrderDetail extends BaseEntity {

    @Column(name = "date_of_reception")
    private LocalDateTime dateOfReceipt;

    @Column(name = "departure_data")
    private LocalDateTime departureDate;

    @Column(name = "closing_date")
    private LocalDateTime closingDate;

    @OneToOne(mappedBy = "detail", cascade = CascadeType.PERSIST)
    private Order order;
}
