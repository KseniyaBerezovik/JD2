package by.itacademy.entity;

import by.itacademy.entity.userEntity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Message extends BaseEntity {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sender_id")
    private User sender;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private LocalDateTime date;
}
