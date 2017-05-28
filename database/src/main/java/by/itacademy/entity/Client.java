package by.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "surname")
    @Getter
    @Setter
    private String surname;
}
