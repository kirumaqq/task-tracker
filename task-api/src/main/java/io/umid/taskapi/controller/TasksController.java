package io.umid.taskapi.controller;


import io.umid.taskapi.dto.CreateTask;
import io.umid.taskapi.dto.EditTask;
import io.umid.taskapi.dto.PageRequestDto;
import io.umid.taskapi.dto.TaskResponse;
import io.umid.taskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/task-api/v1")
public class TasksController {

    private final TaskService taskService;


    @GetMapping("/tasks")
    public ResponseEntity<Page<TaskResponse>> getUserTasks(Principal principal,
                                                           PageRequestDto pageRequestDto) {
        log.info("Reading tasks of user: {}", principal);
        log.debug("Page request: {}", pageRequestDto);

        var tasks = taskService.getUserTasks(principal.getName(), pageRequestDto);
        log.info("Got user tasks: {}", tasks.getContent());

        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskResponse> createTask(Principal principal,
                                                   CreateTask createTask) {
        log.info("Saving task {} for principal {}", createTask, principal);

        var savedTask = taskService.createTask(principal.getName(), createTask);
        log.debug("Saved task: {}", savedTask);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedTask);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(Principal principal,
                                                   @PathVariable("id") Long taskId) {
        log.info("Deleting task, with id {}, of user: {}", taskId, principal);

        taskService.deleteTask(principal.getName(), taskId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/task/{id}")
    public ResponseEntity<?> editTask(Principal principal,
                                                 @PathVariable("id") Long taskId,
                                                 EditTask editTask) {
        log.info("Updating task, with id {}, of user: {}", taskId, principal);
        log.info("Edit task dto: {}", editTask);

        taskService.updateTask(principal.getName(), taskId, editTask);

        return ResponseEntity.noContent().build();
    }



}
