package ibf2022.paf.assessment.server.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;

// TODO: Task 6

@Repository
public class TaskRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String INSERT_TASKLIST = "insert into task (description, priority, due_date, username) values (?, ?, ?, ?)";

    public Integer insertTask(List<Task> taskList) {

        Integer taskCount = 0;

        for (Task t : taskList) {
            Integer taskCreated = jdbcTemplate.update(INSERT_TASKLIST, t.getDescription(),
                t.getPriority(), t.getDueDate(), t.getUsername());
            if (taskCreated > 0) {
                taskCount++;
            }
        }

        return taskCount;
    }

}
