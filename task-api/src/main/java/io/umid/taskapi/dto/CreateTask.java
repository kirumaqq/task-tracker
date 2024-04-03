package io.umid.taskapi.dto;


import io.umid.taskapi.entity.TaskStatus;
import lombok.Value;

import java.sql.Timestamp;
import java.time.Instant;

@Value
public class CreateTask {

    String title;
    String description;
    Timestamp createdAt = Timestamp.from(Instant.now());
    TaskStatus status = TaskStatus.NOT_COMPLETED;
}
