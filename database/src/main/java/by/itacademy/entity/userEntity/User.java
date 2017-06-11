package by.itacademy.entity.userEntity;

import by.itacademy.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends BaseEntity {
    @Column(name = "user_name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
}
