package by.itacademy.entity.orderEntity;

import by.itacademy.entity.otherEntity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_details")
@Data
public class OrderDetail extends BaseEntity {

    @Column(name = "date_of_reception")
    private LocalDateTime dateOfReceipt;

    @Column(name = "departure_data")
    private LocalDateTime departureDate;

    @Column(name = "closing_date")
    private LocalDateTime closingDate;

    @OneToOne(mappedBy = "detail", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Order order;
}
