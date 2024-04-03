package io.umid.taskapi.dto;

import io.umid.taskapi.entity.TaskStatus;

import java.sql.Timestamp;

public record TaskResponse(
        Integer id,
        String title,
        String description,
        TaskStatus status,
        Timestamp createdAt,
        Timestamp completedAt
) {
}
