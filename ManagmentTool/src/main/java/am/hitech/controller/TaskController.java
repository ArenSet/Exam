package am.hitech.controller;

import am.hitech.model.Task;
import am.hitech.model.dto.request.TaskRequestDto;
import am.hitech.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAll(){

        List<Task> tasks = taskService.findAll();

        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid TaskRequestDto requestDto) {

        taskService.create(requestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-project")
    public ResponseEntity<List<Task>> findByProjectId(@RequestParam int projectId){
        List<Task> tasks = taskService.getByProjectId(projectId);

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/by-user")
    @PreAuthorize("hasAuthority('HR') OR hasAuthority('PM') OR hasAuthority('TEAM_LEAD')")
    public ResponseEntity<List<Task>> findByUser(@RequestParam int id){
        List<Task> tasks = taskService.findByUser(id);

        return ResponseEntity.ok(tasks);
    }
}
