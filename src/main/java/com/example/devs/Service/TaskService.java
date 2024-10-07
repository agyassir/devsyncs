package com.example.devs.Service;

import com.example.devs.Entity.Task;
import com.example.devs.Repository.TaskRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class TaskService {
    @Inject
    private  TaskRepository taskreop;


    public Task createTask(Task task){
        return taskreop.save(task);
    }
    public Task updateTask(Task task,long id){
        return taskreop.update(task,id);
    }
}
