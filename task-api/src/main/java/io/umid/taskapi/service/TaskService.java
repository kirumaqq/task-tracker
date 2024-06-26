package io.umid.taskapi.service;

import io.umid.taskapi.dto.CreateTask;
import io.umid.taskapi.dto.EditTask;
import io.umid.taskapi.dto.PageRequestDto;
import io.umid.taskapi.dto.TaskResponse;
import org.springframework.data.domain.Page;

public interface TaskService {

    Page<TaskResponse> getUserTasks(String userId, PageRequestDto pageRequestDto);

    TaskResponse createTask(String userId, CreateTask createTask);

    void deleteTask(String userId, long id);

    void updateTask(String userId, long id, EditTask editTask);
}
