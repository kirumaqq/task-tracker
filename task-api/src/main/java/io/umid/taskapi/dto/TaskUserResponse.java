package io.umid.taskapi.dto;

import io.umid.taskapi.entity.TaskStatus;

import java.sql.Timestamp;

public record TaskUserResponse(
        Integer id,
        String title,
        String description,
        TaskStatus status,
        Timestamp createdAt,
        Timestamp completedAt,
        UserInfo userInfo
) {
}
