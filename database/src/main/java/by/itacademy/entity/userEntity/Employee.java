package by.itacademy.entity.userEntity;

import by.itacademy.entity.Message;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "sender")
    private Set<Message> sentMessages = new HashSet<>();

    @OneToMany(mappedBy = "recipient")
    private Set<Message> receivedMessages = new HashSet<>();
}
