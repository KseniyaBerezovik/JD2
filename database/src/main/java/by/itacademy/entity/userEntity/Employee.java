package by.itacademy.entity.userEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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
