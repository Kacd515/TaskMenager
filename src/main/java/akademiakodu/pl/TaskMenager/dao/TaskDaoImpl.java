package akademiakodu.pl.TaskMenager.dao;

import akademiakodu.pl.TaskMenager.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskDaoImpl implements TaskDao {

    private static List<Task> tasks = new ArrayList<>();
    private static int id = 1;

    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public void add(Task task) {
        task.setId(id++);
        tasks.add(task);
    }

    @Override
    public Task findById(int id) {
        return tasks.stream().
                filter(task -> task.getId()==id).
                findFirst().get();
    }

    @Override
    public void deleteById(int id) {
        Task task = findById(id);
        tasks.remove(task);
    }

    @Override
    public List<Task> findFinished() {
        return tasks.stream().filter(task -> task.isFinished()).collect(Collectors.toList());
    }

    @Override
    public List<Task> findUnfinished() {
        return tasks.stream().filter(task -> !task.isFinished()).collect(Collectors.toList());
    }

    @Override
    public void update(Task task) {
        Task task1 = findById(task.getId());
        task1.setName(task.getName());
        task1.setDescription(task.getDescription());
        task1.setFinished(task.isFinished());
    }
}
