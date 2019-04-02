package mj.pl.taskapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class TaskController  {

    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String allTask(Model model){
        List<Task> taskRepositoryAll = taskRepository.findAll();
        model.addAttribute("tasks",taskRepositoryAll);
        return "homepage";
    }

    @GetMapping("/task/{id}")
    public String taskDetails(@PathVariable Long id, Model model) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if(taskOptional.isPresent()){
            Task task = taskOptional.get();
            model.addAttribute("task", task);
            return "task";
        }else {
            return "redirect:/";
        }
    }

    @GetMapping("/addtask")
    public String addTaskForm(Model model){
        model.addAttribute("task", new Task());
        return "addtaskform";
    }

    @PostMapping("/addtask")
    public String addTask(Task task){
        taskRepository.save(task);
        return "redirect:/";
    }


}
