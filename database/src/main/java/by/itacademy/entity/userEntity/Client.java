package by.itacademy.entity.userEntity;

import by.itacademy.entity.Address;
import by.itacademy.entity.Message;
import by.itacademy.entity.Promotion;
import by.itacademy.entity.orderEntity.Order;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@PrimaryKeyJoinColumn(name = "user_id")
public class Client extends User {
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "owner")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private Set<Message> sentMessages = new HashSet<>();

    @OneToMany(mappedBy = "recipient")
    private Set<Message> receivedMessages = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "promotions_users",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "promotion_id"))
    private Set<Promotion> promotions = new HashSet<>();

}
