package ibf2022.paf.assessment.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

// TODO: Task 7

@Service
public class TodoService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    TaskRepository taskRepo;

    @Transactional
    public Integer upsertTask(User user, List<Task> taskList) {

        Integer status = 0;

        Optional<User> foundUser = userRepo.findUserByUsername(user.getUsername());

        //this 'if' block inserts new User in User table
        if (foundUser.isEmpty()) {
            //this insertUser returns unique userId in String
            //if 0 means user not created, return 0 for downstream handling
            String userId = userRepo.insertUser(user); 
            if (null == userId) {
                status = 0;
            }
        }

        //this try-catch block inserts new tasks in Task table.  
        //insertTask will return number of tasksInserted
        //if tasksInserted is 0, means some error and tasks not inserted
        //return 0 for downstream handling
        //return tasksInserted if successful insertion
        try {
            Integer tasksInserted = taskRepo.insertTask(taskList);
            if (tasksInserted > 0) {
                return tasksInserted;
            } else {
                return status;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return status;
        }
        
    }

}
