package io.umid.taskapi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@FieldNameConstants
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "t_users", schema = "task_api")
public class User {

    @Id
    String id;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "username")
    String username;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Task> tasks;
}
