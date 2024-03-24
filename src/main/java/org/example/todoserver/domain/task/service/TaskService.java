package org.example.todoserver.domain.task.service;

import lombok.RequiredArgsConstructor;
import org.example.todoserver.domain.task.constants.TaskStatus;
import org.example.todoserver.domain.task.entity.TaskEntity;
import org.example.todoserver.domain.task.model.TaskRequest;
import org.example.todoserver.domain.task.model.TaskResponse;
import org.example.todoserver.domain.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<TaskResponse> getTask(Optional<String> dueDate) {
        if(dueDate.isPresent()) {
            return getByDueDate(dueDate.get());
        } else {
            return getAll();
        }
    }

    private List<TaskResponse> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private List<TaskResponse> getByDueDate(String dueDate) {
        return taskRepository.findAllByDueDate(Date.valueOf(dueDate))
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<TaskResponse> getByStatus(TaskStatus status) {
        return taskRepository.findAllByStatus(status)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public TaskResponse getOne(Long id) {
        var entity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("not exists task id [%d]", id)));

        return toResponse(entity);
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
