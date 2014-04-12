package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Task;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class TasksApiController extends Controller{

    public static Result allTasks(){
        List<Task> tasks = Task.all();
        JsonNode tasksJson = Json.toJson(tasks);
        return ok(tasksJson);
    }

    public static Result task(Long id){
        Task task = Task.getTask(id);
        JsonNode tasksJson = Json.toJson(task);
        return ok(tasksJson);
    }

    public static Result newTask(){
        Task task = Json.fromJson(request().body().asJson(), Task.class);
        Task.create(task);
        return ok(Json.toJson(task));
    }

    public static Result deleteTask(Long id){
        Task.delete(id);
        return ok("Success!");
    }
}
