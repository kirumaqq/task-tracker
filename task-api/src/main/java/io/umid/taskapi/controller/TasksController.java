package io.umid.taskapi.controller;


import io.umid.taskapi.dto.PageRequestDto;
import io.umid.taskapi.dto.TaskResponse;
import io.umid.taskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/task-api/v1")
public class TasksController {

    private final TaskService taskService;


    @GetMapping("/user/tasks")
    public ResponseEntity<Page<TaskResponse>> getUserTasks(Principal principal,
                                                           PageRequestDto pageRequestDto) {
        log.info("Reading user, {}, tasks", principal);
        log.debug("Page request: {}", pageRequestDto);

        var tasks = taskService.getUserTasks(principal.getName(), pageRequestDto);
        log.info("Got user tasks: {}", tasks);

        return ResponseEntity.ok(tasks);
    }



}
