package by.itacademy.entity.userEntity;

import by.itacademy.entity.otherEntity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
@Data
public class Profile extends BaseEntity {
    @Column(name = "telephone")
    private String telephone;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
