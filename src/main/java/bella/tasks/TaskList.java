package bella.tasks;

public class TaskList {
    private final Task[] tasks;
    private int count;

    public TaskList() {
        this.tasks = new Task[100];
        this.count = 0;
    }

    public void add(Task task) {
        tasks[count] = task;
        count++;
    }

    public Task get(int index) {
        return tasks[index];
    }

    public int getCount() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
