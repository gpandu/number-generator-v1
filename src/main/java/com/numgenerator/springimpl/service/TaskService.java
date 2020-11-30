package com.numgenerator.springimpl.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.numgenerator.springimpl.bean.Task;
import com.numgenerator.springimpl.bean.TaskStatus;
import com.numgenerator.springimpl.util.NumberUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TaskService {

    Map<String, List<Task>> taskMap =  null;
    Map<String, TaskStatus>  taskStatus = null;
    ObjectMapper objectMapper = null;

    public TaskService(){
        taskMap = new ConcurrentHashMap<>();
        taskStatus = new ConcurrentHashMap<>();
        objectMapper = new ObjectMapper();
    }

    public String generateBulkTaskId(List<Task> tasks){
        String uuid = UUID.randomUUID().toString();
        List<Task> taskList = new ArrayList<>(tasks);
        taskMap.put(uuid, taskList);
        return uuid;
    }

    public String generateTaskId(Task task){
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        return generateBulkTaskId(taskList);
    }

    public Map<String, String> getTasks(String uuid ) throws InterruptedException, JsonProcessingException {
        List<Task> tasks = taskMap.get(uuid);
        List<String> taskResults = NumberUtil.getTaskResult(tasks);
        if(taskResults.isEmpty()) return null;
        Map<String, String> map = new HashMap<>();
        if(taskResults.size()>1){
            map.put("results", String.valueOf(objectMapper.writeValueAsString(taskResults)));
        }else{
            map.put("result", taskResults.get(0));
        }
        return map;
    }

    public String getTaskStatus(String uuid){
          return taskStatus.get(uuid) != null ? taskStatus.get(uuid).toString() : null;
    }

    public void updateStatus(String uuid, TaskStatus status){
        taskStatus.put(uuid, status);
    }
}


