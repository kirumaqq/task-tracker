package io.umid.taskapi.service;

import io.umid.taskapi.dto.PageRequestDto;
import io.umid.taskapi.dto.TaskResponse;
import org.springframework.data.domain.Page;

public interface TaskService {

    Page<TaskResponse> getUserTasks(String userId, PageRequestDto pageRequestDto);
}
