package by.itacademy.entity.userEntity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "user_id")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class Employee extends User {
    @Column(name = "payment")
    private Double payment;
}
