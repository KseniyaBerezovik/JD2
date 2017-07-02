package by.itacademy.entity.userEntity;

import by.itacademy.entity.otherEntity.BaseEntity;
import lombok.*;

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
}
