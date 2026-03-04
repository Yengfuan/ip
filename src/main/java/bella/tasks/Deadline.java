package bella.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description and a due date-time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a new {@code Deadline} with the specified description and due date-time.
     *
     * @param description The description of the deadline task.
     * @param by The due date and time of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the due date and time of the deadline.
     *
     * @return The due {@link LocalDateTime}.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns the type icon for a deadline task.
     *
     * @return {@code "[D]"}.
     */
    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    /**
     * Returns a string representation of the deadline task including its due date-time.
     *
     * @return A formatted string (e.g., {@code "[D][ ] return book (by: Mar 10 2026 12:00pm)"}).
     */
    @Override
    public String toString() {
        return getTypeIcon() + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma")) + ")";
    }

}
