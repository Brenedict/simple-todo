package com.example.todo;

import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import com.example.todo.repository.TaskRepository;
import io.cucumber.java.en.*;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest
public class TaskStepDefinitions {

    @Autowired 
    private TaskService taskService;
    
    @Autowired 
    private TaskRepository taskRepository;

    @Given("the following tasks exist:")
    public void the_following_tasks_exist(List<Map<String, String>> dataTable) {
        taskRepository.deleteAll();
        for (Map<String, String> row : dataTable) {
            Task t = new Task();
            t.setTitle(row.get("title"));
            t.setStatus(Boolean.parseBoolean(row.get("status")));
            taskService.saveTask(t);
        }
    }

    @When("I request to clear {string} tasks")
    public void i_request_to_clear_tasks(String type) {
        boolean onlyActive = type.equalsIgnoreCase("active");
        taskService.deleteAllTasks(onlyActive);
    }

    @Then("there should be {int} task remaining")
    public void there_should_be_task_remaining(Integer expectedCount) {
        assertEquals((long) expectedCount, taskRepository.count());
    }

    @Then("the remaining task should be {string}")
    public void the_remaining_task_should_be(String title) {
        Task t = taskRepository.findAll().get(0);
        assertEquals(title, t.getTitle());
    }

    @When("I mark the 0th task as {string}")
    public void i_mark_task_as(String type) {
        boolean isDone = type.equalsIgnoreCase("done");
        Task t = taskRepository.findAll().get(0);
        t.setStatus(isDone);
        taskService.saveTask(t);
    }

    @Then("there should be {int} tasks with status {string}")
    public void there_should_be_nth_task_with_status(Integer expectedCount, String expectedStatus) {
        boolean status = expectedStatus.equalsIgnoreCase("true");

        long actualCount = status ? taskRepository.countByStatusTrue() : taskRepository.countByStatusFalse();

        assertEquals((long) expectedCount, actualCount);
    }

    @Given("the database has {int} tasks")
    public void the_database_has_tasks(Integer count) {
        taskRepository.deleteAll();
        for (int i = 0; i < count; i++) {
            Task t = new Task();
            String taskTitle = "Task #" + (i+1);
            t.setTitle(taskTitle);
            t.setStatus(i % 2 == 0);
            taskService.saveTask(t);
        }
    }

    @When("I add {int} new tasks")
    public void i_add_new_tasks(Integer count) {
        for (int i = 0; i < count; i++) {
            Task task = new Task();
            task.setTitle("Automated Task " + i);
            task.setStatus(false);
            taskService.saveTask(task);
        }
    }

    @When("I delete {int} tasks")
    public void i_delete_tasks(Integer count) {
        List<Task> tasks = taskRepository.findAll();
        for (int i = 0; i < count && i < tasks.size(); i++) {
            taskRepository.delete(tasks.get(i));
        }
    }
}