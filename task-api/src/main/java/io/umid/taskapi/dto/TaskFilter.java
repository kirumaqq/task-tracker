package io.umid.taskapi.dto;

import lombok.Value;

import java.sql.Timestamp;

@Value
public class TaskFilter {

    Timestamp createdAtLowerBound;

    Timestamp createdAtUpperBound;

    Timestamp completedAtLowerBound;

    Timestamp completedAtUpperBound;


}
