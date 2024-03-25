package org.example.todoserver.domain.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.todoserver.domain.task.constants.TaskStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatusRequest {

    private TaskStatus status;
}
