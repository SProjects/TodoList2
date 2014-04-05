import models.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class TaskTest {
    private Task fakeTaskOne;
    private Task fakeTaskTwo;


    @Before
    public void setUp(){
        fakeTaskOne = createFakeTask("Task 1", "Task 1 Content");
        fakeTaskTwo = createFakeTask("Task 2", "Task 2 has different content");
    }

    @Test
    public void shouldPickAllTasksFromTheDatabase(){
        running(fakeApplication(), new Runnable() {
            public void run() {
                fakeTaskOne.save();
                fakeTaskTwo.save();
                List<Task> allTasks = Task.all();
                assertThat(allTasks.size(), is(2));
                assertThat(allTasks.get(0), is(fakeTaskOne));
                assertThat(allTasks.get(1), is(fakeTaskTwo));
            }
        });

    }

    @Test
    public void shouldAddTasksToTheDatabase(){
        running(fakeApplication(), new Runnable() {
            public void run() {
                fakeTaskOne.save();
                fakeTaskTwo.save();
                Task fakeTask3 = createFakeTask("Task 3", "Task 3 has different content");
                Task.create(fakeTask3);
                List<Task> allTasks = Task.all();
                assertThat(allTasks.size(), is(3));
            }
        });

    }

    @Test
    public void shouldDeleteTaskFromTheDatabase(){
        running(fakeApplication(), new Runnable() {
            public void run() {
                fakeTaskOne.save();
                fakeTaskTwo.save();
                Task.delete(fakeTaskTwo.id);
                List<Task> allTaks = Task.all();
                assertThat(allTaks.size(), is(1));
            }
        });
    }

    private Task createFakeTask(String taskTitle, String taskDescription){
        Task task = new Task();
        task.title = taskTitle;
        task.description = taskDescription;
        return task;
    }
}
