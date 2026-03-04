package bella.tasks;


/**
 * Represents a generic task with a description and completion status.
 *
 * <p>This is the base class for all task types (e.g., {@link Todo}, {@link Deadline}, {@link Event}).</p>
 */
public class Task {
    protected String description;
    protected boolean isDone;


    /**
     * Constructs a new {@code Task} with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return {@code "X"} if the task is done, or a blank space if not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task including its status and description.
     *
     * @return A formatted string (e.g., {@code "[ ] read book"}).
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }


    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the type icon for this task.
     * Subclasses should override this to return their specific icon.
     *
     * @return An empty string by default.
     */
    public String getTypeIcon() { return ""; }
}
