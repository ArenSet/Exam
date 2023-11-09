package am.hitech.repository;

import am.hitech.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {


    List<Task> findAll();

    List<Task> getByProjectId(int projectId);

    List<Task> getByAssigneeId(int id);

}
