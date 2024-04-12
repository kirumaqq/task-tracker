package io.umid.taskapi.service;

import io.umid.taskapi.dto.TasksFilter;
import io.umid.taskapi.dto.UserTasksResponse;

import java.util.List;

public interface UserService {

    List<UserTasksResponse> getAllUsersTasks(TasksFilter tasksFilter);

}
