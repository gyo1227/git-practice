package org.example.todoserver.domain.task.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todoserver.domain.task.constants.TaskStatus;
import org.example.todoserver.domain.task.model.ResultResponse;
import org.example.todoserver.domain.task.model.TaskRequest;
import org.example.todoserver.domain.task.model.TaskResponse;
import org.example.todoserver.domain.task.model.TaskStatusRequest;
import org.example.todoserver.domain.task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    /**
     * 특정 마감일에 해당하는 할일 목록을 반환
     * @param dueDate 할일의 마감일
     * @return 마감일에 해당하는 할일 목록
     */
    @GetMapping("")
    public ResponseEntity<List<TaskResponse>> getTask(Optional<String> dueDate) {
        var response = taskService.getTask(dueDate);
        return ResponseEntity.ok(response);
    }

    /**
     * 특정 ID에 해당하는 할일을 조회
     * @param id 할일 ID
     * @return ID에 해당하는 할일 객체
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> fetchOneTask(@PathVariable Long id) {
        var response = taskService.getOne(id);
        return ResponseEntity.ok(response);
    }

    /**
     * 특정 상태에 해당하는 할일 목록을 반환
     * @param status 할일 상태
     * @return 상태에 해당하는 할일 목록
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponse>> getByStatus(@PathVariable TaskStatus status) {
        var response = taskService.getByStatus(status);
        return ResponseEntity.ok(response);
    }

    /**
     * 특정 ID에 해당하는 할일을 수정
     * @param id 할일 ID
     * @param taskRequest 수정할 할일 정보
     * @return 수정된 할일 객체
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        var response = taskService.update(id, taskRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * 특정 ID에 해당하는 할일의 상태를 수정
     * @param id 할일 ID
     * @param taskStatusRequest 수정할 할일 상태 정보
     * @return 수정된 할일 객체
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(@PathVariable Long id, @RequestBody TaskStatusRequest taskStatusRequest) {
        var response = taskService.updateStatus(id, taskStatusRequest.getStatus());
        return ResponseEntity.ok(response);
    }

    /**
     * 특정 ID에 해당하는 할일을 삭제
     * @param id 삭제할 할일 ID
     * @return 삭제 결과를 담은 응답 객체
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResultResponse> deleteTask(@PathVariable Long id) {
        var response = taskService.delete(id);
        return ResponseEntity.ok(new ResultResponse(response));
    }

}
