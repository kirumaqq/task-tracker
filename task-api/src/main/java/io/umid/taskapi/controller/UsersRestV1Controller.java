package io.umid.taskapi.controller;

import io.umid.taskapi.dto.TasksFilter;
import io.umid.taskapi.dto.UserTasksResponse;
import io.umid.taskapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("task-api/v1")
public class UsersRestV1Controller {

    private final UserService userService;


    @GetMapping("/users/tasks")
    public ResponseEntity<List<UserTasksResponse>> getUsersTasks(TasksFilter tasksFilter) {
        log.info("Fetching all tasks by filter: {}", tasksFilter);

        List<UserTasksResponse> tasks = userService.getAllUsersTasks(tasksFilter);
        log.info("Tasks: {}", tasks);

        return ResponseEntity.ok(tasks);
    }
}
