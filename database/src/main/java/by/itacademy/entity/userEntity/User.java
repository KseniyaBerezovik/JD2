package by.itacademy.entity.userEntity;

import by.itacademy.entity.otherEntity.Address;
import by.itacademy.entity.otherEntity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {
    @Column(name = "user")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
}
