package org.example.todoserver.domain.task.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todoserver.domain.task.model.TaskRequest;
import org.example.todoserver.domain.task.model.TaskResponse;
import org.example.todoserver.domain.task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * 새로운 할 일 추가
     * @param request 추가하고자 하는 할 일
     * @return 추가된 할 일
     */
    @PostMapping("")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request) {
        var response = taskService.add(request);
        return ResponseEntity.ok(response);
    }
}
