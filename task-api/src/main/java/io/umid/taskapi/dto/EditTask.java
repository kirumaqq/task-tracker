package io.umid.taskapi.dto;

import io.umid.taskapi.entity.TaskStatus;
import lombok.Value;

import java.sql.Timestamp;
import java.time.Instant;

@Value
public class EditTask {

    String title;
    String description;
    TaskStatus status;
    Timestamp completedAt;

    public EditTask(String title, String description, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.completedAt = completedAtByStatus(status);
    }

    private Timestamp completedAtByStatus(TaskStatus status) {
        return status == TaskStatus.COMPLETED ? Timestamp.from(Instant.now()) : null;
    }
}
