package io.umid.taskapi.dto;

import java.util.List;

public record UserTasksResponse(

        String username,
        String firstName,
        String lastName,
        String email,
        List<TaskResponse> tasks
) {
}
