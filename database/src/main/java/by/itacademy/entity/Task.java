package by.itacademy.entity;


import by.itacademy.entity.userEntity.Admin;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
public class Task extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private TaskStatus status;

    @Column(name = "date")
    private LocalDateTime dateOfReceipts;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "admin_id")
    private Admin admin;
}