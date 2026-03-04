package bella.tasks;

import java.util.ArrayList;

/**
 * Manages a collection of {@link Task} objects.
 *
 * <p>Provides methods to add, retrieve, delete, and search tasks.</p>
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The {@link Task} to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task at the specified 0-based index.
     *
     * @param index The 0-based position of the task.
     * @return The {@link Task} at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getCount() {
        return tasks.size();
    }

    /**
     * Removes a task at the specified 0-based index.
     *
     * @param index The 0-based position of the task to remove.
     */
    public void delete(int index) { tasks.remove(index); }

    /**
     * Checks whether the task list is empty.
     *
     * @return {@code true} if there are no tasks; {@code false} otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Searches for tasks whose descriptions contain the specified keyword.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for.
     * @return A {@link TaskList} containing all matching tasks.
     */
    public TaskList findByKeyword(String keyword) {
        TaskList results = new TaskList();
        for (int i = 0; i < getCount(); i++) {
            if (tasks.get(i).getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(tasks.get(i));
            }
        }
        return results;
    }
}
