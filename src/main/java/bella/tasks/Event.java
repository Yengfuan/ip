package bella.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.toString() + " (from: "
                + getFrom().format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"))
                + " to: "
                + getTo().format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"))
                + ")";
    }
}
