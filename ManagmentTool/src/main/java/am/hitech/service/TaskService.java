package am.hitech.service;

import am.hitech.model.Task;
import am.hitech.model.dto.request.TaskRequestDto;

import java.util.List;

public interface TaskService {
    List<Task> findAll();

    List<Task> getByProjectId(int projectId);

    List<Task> findByUser(int id);

    void create(TaskRequestDto requestDto);
}
