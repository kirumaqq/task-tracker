package io.umid.taskapi.service.impl;

import io.umid.taskapi.dto.PageRequestDto;
import io.umid.taskapi.dto.TaskResponse;
import io.umid.taskapi.mapper.TaskMapper;
import io.umid.taskapi.repository.TaskRepository;
import io.umid.taskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    @Override
    public Page<TaskResponse> getUserTasks(String userId, PageRequestDto pageRequestDto) {
        var pageRequest = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize(),
                pageRequestDto.getDirection(), pageRequestDto.getSortBy());
        log.debug("Formed page request: {}. Sending request to repository", pageRequest);

        return taskRepository.getAllByUserId(userId, pageRequest)
                .map(taskMapper::toResponse);
    }

}
