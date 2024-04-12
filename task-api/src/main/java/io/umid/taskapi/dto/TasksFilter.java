package io.umid.taskapi.dto;

import io.umid.taskapi.entity.TaskStatus;

import java.sql.Timestamp;

public record TasksFilter(

        Timestamp completedAtLowerBound,
        Timestamp completedAtUpperBound,
        TaskStatus status
) {
}
