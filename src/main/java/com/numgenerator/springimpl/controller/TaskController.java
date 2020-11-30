package com.numgenerator.springimpl.controller;

import com.numgenerator.springimpl.bean.Task;
import com.numgenerator.springimpl.bean.TaskStatus;
import com.numgenerator.springimpl.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping(value="api/generate")
    public ResponseEntity generateTask(@RequestBody Task task){
        String uuid = taskService.generateTaskId(task);
        Map<String, String> map = new HashMap<>();
        map.put("task", uuid);
        return ResponseEntity.accepted().body(map);
    }

    @PostMapping(value="api/bulkGenerate")
    public ResponseEntity generateBulkTask(@RequestBody List<Task> tasks){
        String uuid = taskService.generateBulkTaskId(tasks);
        Map<String, String> map = new HashMap<>();
        map.put("task", uuid);
        return ResponseEntity.accepted().body(map);
    }

    @GetMapping(value="api/tasks/{uuid}")
    public ResponseEntity getNumbers(@PathVariable String uuid, @RequestParam String action){
        if(action.equals("get_numlist")){
            try{
                taskService.updateStatus(uuid, TaskStatus.IN_PROGRESS);
                Map<String, String> result = taskService.getTasks(uuid);
                taskService.updateStatus(uuid, TaskStatus.SUCCESS);
                return ResponseEntity.ok(result);
            }catch(Exception e){
                taskService.updateStatus(uuid, TaskStatus.ERROR);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(method = RequestMethod.GET, value="api/tasks/{uuid}/status")
    public ResponseEntity getTaskStatus(@PathVariable  String uuid){
       if(taskService.getTaskStatus(uuid) != null){
           return ResponseEntity.ok(taskService.getTaskStatus(uuid));
       }
       return ResponseEntity.noContent().build();
    }


}
