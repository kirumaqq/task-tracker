package io.umid.taskapi.service.impl;

import io.umid.taskapi.dto.CreateTask;
import io.umid.taskapi.dto.EditTask;
import io.umid.taskapi.dto.PageRequestDto;
import io.umid.taskapi.dto.TaskResponse;
import io.umid.taskapi.entity.Task;
import io.umid.taskapi.entity.TaskStatus;
import io.umid.taskapi.entity.User;
import io.umid.taskapi.exception.ResourceNotFoundException;
import io.umid.taskapi.mapper.TaskMapper;
import io.umid.taskapi.repository.TaskRepository;
import io.umid.taskapi.service.TaskService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<TaskResponse> getUserTasks(String userId, PageRequestDto pageRequestDto) {
        var pageRequest = formPageRequest(pageRequestDto);

        log.debug("Formed page request: {}. Sending request to repository", pageRequest);

        return taskRepository.getAllByUserId(userId, pageRequest)
                .map(taskMapper::toResponse);
    }

    private static PageRequest formPageRequest(PageRequestDto pageRequestDto) {
        var pageRequest = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        if (!pageRequestDto.isDefaultSort()) {
            pageRequest = pageRequest.withSort(pageRequestDto.getDirection(), pageRequestDto.getSortBy());
        }
        return pageRequest;
    }

    @Transactional
    @Override
    public TaskResponse createTask(String userId, CreateTask createTask) {

        Task task = taskMapper.fromCreateTask(createTask);

        User userReference = entityManager.getReference(User.class, userId);
        task.setUser(userReference);

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

        taskMapper.updateTask(editTask, task);

        if (editTask.getStatus() == TaskStatus.NOT_COMPLETED) {
            task.setCompletedAt(null);
        }

        log.debug("Updated task: {}", task);
    }




}
