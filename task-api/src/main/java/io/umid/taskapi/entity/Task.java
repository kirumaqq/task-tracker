package io.umid.taskapi.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "t_tasks", schema = "task_api")
public class Task {

    @Id
    Integer id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description")
    String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Enumerated(value = EnumType.STRING)
    @JoinColumn(name = "status", nullable = false)
    TaskStatus status;

    @Column(name = "created_at", nullable = false)
    Timestamp createdAt;

    @Column(name = "done_at")
    Timestamp completedAt;
}
