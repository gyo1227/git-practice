package org.example.todoserver.domain.task.service;

import lombok.RequiredArgsConstructor;
import org.example.todoserver.domain.task.constants.TaskStatus;
import org.example.todoserver.domain.task.entity.TaskEntity;
import org.example.todoserver.domain.task.model.TaskRequest;
import org.example.todoserver.domain.task.model.TaskResponse;
import org.example.todoserver.domain.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;


    public TaskResponse add(TaskRequest request) {
        var entity = TaskEntity.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .dueDate(Date.valueOf(request.getDueDate()))
                .status(TaskStatus.TODO)
                .build();

        var saved = taskRepository.save(entity);

        return toResponse(saved);
    }

    private TaskResponse toResponse(TaskEntity entity) {
        return TaskResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .dueDate(entity.getDueDate().toString())
                .createdAt(entity.getCreatedAt().toLocalDateTime())
                .updatedAt(entity.getUpdatedAt().toLocalDateTime())
                .build();
    }
}
