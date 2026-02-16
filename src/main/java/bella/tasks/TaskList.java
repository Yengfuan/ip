package bella.tasks;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int getCount() {
        return tasks.size();
    }

    public void delete(int index) { tasks.remove(index); }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
