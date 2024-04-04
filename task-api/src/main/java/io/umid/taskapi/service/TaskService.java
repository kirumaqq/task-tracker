package io.umid.taskapi.service;

import io.umid.taskapi.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {

    Page<TaskResponse> getUserTasks(String userId, PageRequestDto pageRequestDto);

    TaskResponse createTask(String userId, CreateTask createTask);

    void deleteTask(String userId, long id);

    void updateTask(String userId, long id, EditTask editTask);

    List<TaskUserResponse> getAllTasks(TaskFilter taskFilter);
}
