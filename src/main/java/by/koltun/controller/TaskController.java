package by.koltun.controller;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.rest.service.api.runtime.task.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> tasks(@RequestParam(value="orderBy", required = false) final String orderBy,
                                                    @RequestParam(value="order", required = false) final String order) {
        TaskQuery taskQuery = taskService.createTaskQuery();
        processOrderClause(taskQuery, orderBy, order);

        final List<TaskResponse> result = taskQuery.orderByTaskCreateTime().asc().list().stream()
                .map(TaskResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> task(@PathVariable("id") final String id) {
        final Task task;
        if ((task = taskService.createTaskQuery().taskId(id).singleResult()) == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new TaskResponse(task));
    }

    private void processOrderClause(final TaskQuery taskQuery, final String orderBy, final String order) {
        final String orderByField = Optional.ofNullable(orderBy).orElse("createTime");
        switch (orderByField) {
            case "createTime":
                taskQuery.orderByTaskCreateTime();
                break;
        }

        final boolean ascending = Optional.ofNullable(order).filter(Arrays.asList("asc", "desc")::contains)
                .map("asc"::equals).orElse(false);
        System.out.println("Order by " + orderBy + " ascending: " + ascending);

        if (ascending) {
            taskQuery.asc();
        } else {
            taskQuery.desc();
        }
    }
}
