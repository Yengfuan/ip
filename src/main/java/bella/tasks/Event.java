package bella.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description, start time, and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new {@code Event} with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the end date and time of the event.
     *
     * @return The end {@link LocalDateTime}.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Returns the start date and time of the event.
     *
     * @return The start {@link LocalDateTime}.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Returns the type icon for an event task.
     *
     * @return {@code "[E]"}.
     */
    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    /**
     * Returns a string representation of the event task including its time range.
     *
     * @return A formatted string (e.g., {@code "[E][ ] project meeting (from: Mar 05 2026 04:15pm to: Mar 05 2026 05:15pm)"}).
     */
    @Override
    public String toString() {
        return getTypeIcon() + super.toString() + " (from: "
                + getFrom().format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"))
                + " to: "
                + getTo().format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"))
                + ")";
    }
}
