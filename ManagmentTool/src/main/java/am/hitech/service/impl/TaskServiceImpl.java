package am.hitech.service.impl;

import am.hitech.model.Task;
import am.hitech.model.dto.request.TaskRequestDto;
import am.hitech.repository.TaskRepository;
import am.hitech.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getByProjectId(int projectId){
        List<Task> tasks = taskRepository.getByProjectId(projectId);

        return tasks;
    }

    @Override
    public List<Task> findByUser(int id){
        List<Task> tasks = taskRepository.getByAssigneeId(id);

        return tasks;
    }
    @Override
    @Transactional
    public void create(TaskRequestDto requestDto) {

        Task task = convertToTask(requestDto, new Task());

        task = taskRepository.saveAndFlush(task);
        System.out.println(task);
    }

    private Task convertToTask(TaskRequestDto requestDto, Task task) {
        task.setCreatorId(requestDto.getCreatorId());
        task.setSummary(requestDto.getSummary());
        task.setStatus(requestDto.getStatus());
        task.setPriority(requestDto.getPriority());
        task.setProgress(requestDto.getProgress());
        task.setCreatedDate(System.currentTimeMillis());

        return task;
    }

}
