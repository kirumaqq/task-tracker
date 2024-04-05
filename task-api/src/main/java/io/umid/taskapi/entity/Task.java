package io.umid.taskapi.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.sql.Timestamp;
@FieldNameConstants
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description")
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Enumerated(value = EnumType.STRING)
    @JoinColumn(name = "status", nullable = false)
    TaskStatus status;

    @Column(name = "created_at", nullable = false)
    Timestamp createdAt;

    @Column(name = "completed_at")
    Timestamp completedAt;
}
