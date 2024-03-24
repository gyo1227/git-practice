package org.example.todoserver.domain.task.repository;

import org.example.todoserver.domain.task.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {


}
