package by.itacademy.entity.userEntity;

import by.itacademy.entity.otherEntity.Address;
import by.itacademy.entity.otherEntity.Promotion;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "address")
@PrimaryKeyJoinColumn(name = "user_id")
public class Client extends User {
    @Embedded
    private Address address;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "promotions_users",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "promotion_id"))
    private Set<Promotion> promotions = new HashSet<>();

}
