package ibf2022.paf.assessment.server.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.services.TodoService;

// TODO: Task 4, Task 8

@Controller
@RequestMapping
public class TasksController {

    @Autowired
    TodoService todoSvc;

    @PostMapping(path="/task", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postTask(@RequestBody MultiValueMap<String, String> form, Model model) {
        
        List<Task> taskList = new LinkedList<>();
        List<String> descriptions = new LinkedList<>();
        List<String> priority = new LinkedList<>();
        List<String> dueDate = new LinkedList<>();
        User user = new User();
        

        String username = form.getFirst("username");
        user.setUsername(username);

        //populate individual tasks into taskList, each task will have username as identifier

        for (String desc : form.keySet()) {
            if (desc.startsWith("description-")) { 
                descriptions.addAll(form.get(desc));
                // descriptions = form.get(desc);                
            }
        }

        System.out.println(descriptions.toString());

        for (String pty : form.keySet()) {
            if (pty.startsWith("priority-")) { 
                priority.addAll(form.get(pty));
                // priority = form.get(pty); 
            }
        }

        for (String dd : form.keySet()) {
            if (dd.startsWith("dueDate-")) { 
                dueDate.addAll(form.get(dd));
                // dueDate = form.get(dd);
            }
        }
                
        for (int i = 0; i < descriptions.size(); i++) {
            Task task = new Task();
            task.setUsername(username);
            task.setTaskId(i);
            task.setDescription(descriptions.get(i));
            task.setPriority(Integer.parseInt(priority.get(i)));
            task.setDueDate(dueDate.get(i));
            taskList.add(task);
        }

        System.out.println(taskList.toString());

        Integer tasksInserted = 0;
        
        //just need to find username in User object to create new user if necessary,
        //since username is primary key and set "not null"
        //tasksInserted will also check if creation of user is successful from TodoService
        tasksInserted = todoSvc.upsertTask(user, taskList);

        //find a return for taskcount
        model.addAttribute("taskCount", tasksInserted);
        model.addAttribute("username", username);


        if (tasksInserted > 0) {
            return "result";
        } else {
            return "error";
        }
    }
}


        // int taskId = 0;

        // for (String input : form.keySet()) {

        //     if (input.startsWith("description-")) { 
        //         descriptions = form.get(input);                
        //     }
        //     if (input.startsWith("priority-")) { 
        //         priority = form.get(input); 
        //     }
        //     if (input.startsWith("dueDate-")) { 
        //         dueDate = form.get(input);
        //     }

        //     taskId++;        

        //     Task task = new Task();
        //     task.setUsername(username);
        //     task.setTaskId(taskId);
        //     task.setDescription(descriptions.get(taskId));
        //     task.setPriority(Integer.parseInt(priority.get(taskId)));
        //     task.setDueDate(dueDate.get(taskId));
        //     taskList.add(task);
            
        // }