package bella.tasks;

/**
 * Represents a todo task with only a description and no deadline or time constraint.
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type icon for a todo task.
     *
     * @return {@code "[T]"}.
     */
    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A formatted string (e.g., {@code "[T][ ] read book"}).
     */
    @Override
    public String toString() {
        return getTypeIcon() + super.toString();
    }

}
