package com.numgenerator.springimpl.webTest;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numgenerator.springimpl.bean.Task;
import com.numgenerator.springimpl.service.TaskService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TaskService taskService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void TestGenerateTask() throws Exception {
        Task task = new Task("4", "2");
        this.mockMvc.perform(post("/api/generate" ).content(mapper.writeValueAsString(task)).contentType("application/json")).andDo(print()).andExpect(status().isAccepted())
                .andExpect(content().string(containsString("task")));
    }

    @Test
    public void TestGenerateBulkTask() throws Exception {
        Task task1 = new Task("4", "2");
        Task task2 = new Task("2", "2");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        this.mockMvc.perform(post("/api/bulkGenerate" ).content(mapper.writeValueAsString(tasks)).contentType("application/json")).andDo(print()).andExpect(status().isAccepted())
                .andExpect(content().string(containsString("task")));
    }

    @Test
    public void TestGetNumbers() throws Exception {
        Task task = new Task("4", "2");
        String uuid = taskService.generateTaskId(task);
        Map<String, String> map = new HashMap<>();
        map.put("result","4,2,0");
        this.mockMvc.perform(get("/api/tasks/"+uuid+"?action=get_numlist")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(mapper.writeValueAsString(map))));
    }


}
