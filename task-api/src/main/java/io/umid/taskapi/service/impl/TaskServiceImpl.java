package io.umid.taskapi.service.impl;

import io.umid.taskapi.dto.*;
import io.umid.taskapi.entity.Task;
import io.umid.taskapi.entity.TaskStatus;
import io.umid.taskapi.entity.User;
import io.umid.taskapi.exception.ResourceNotFoundException;
import io.umid.taskapi.mapper.TaskMapper;
import io.umid.taskapi.repository.TaskRepository;
import io.umid.taskapi.service.TaskService;
import io.umid.taskapi.specs.TaskSpecs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Transactional
    @Override
    public TaskResponse createTask(String userId, CreateTask createTask) {

        Task task = taskMapper.fromCreateTask(createTask);
        User associatedUser = new User();
        associatedUser.setId(userId);
        task.setUser(associatedUser);

        log.debug("Saving task {} for user with id: {}", task, userId);
        Task savedTask = taskRepository.save(task);

        return taskMapper.toResponse(savedTask);
    }

    @Transactional
    @Override
    public void deleteTask(String userId, long id) {

        log.debug("Fetching task by id: {} of user with id: {}", id, userId);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find task by id: " + id));

        if (!userId.equals(task.getUser().getId())) {
            throw new AccessDeniedException("Task user id and requested user id didn't match");
        }

        log.debug("Deleting task: {}", task);
        taskRepository.delete(task);
    }

    @Transactional
    @Override
    public void updateTask(String userId, long id, EditTask editTask) {

        log.debug("Fetching task by id: {} of user with id: {}", id, userId);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find task by id: " + id));

        if (!userId.equals(task.getUser().getId())) {
            throw new AccessDeniedException("Task user id and requested user id didn't match");
        }

        if (editTask.getStatus() == TaskStatus.COMPLETED) {
            task.setCompletedAt(null);
        }

        taskMapper.updateTask(editTask, task);
        log.debug("Updated task: {}", task);
    }


    @Override
    public List<TaskUserResponse> getAllTasks(TaskFilter taskFilter) {

        var createdAtSpec = TaskSpecs.timestampBetween(taskFilter.getCreatedAtLowerBound(),
                taskFilter.getCreatedAtUpperBound(), Task.Fields.createdAt);
        var completedAtSpec = TaskSpecs.timestampBetween(taskFilter.getCompletedAtLowerBound(),
                taskFilter.getCreatedAtUpperBound(), Task.Fields.completedAt);
        var spec = createdAtSpec.and(completedAtSpec);

        var tasks = taskRepository.findAll(spec);

        return tasks
                .stream()
                .map(taskMapper::toTaskUserResponse)
                .toList();
    }


}
