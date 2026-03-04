package bella.commands;

import bella.EmptyFieldException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static bella.Parser.*;

public class CommandProducer {
    public static Command createTodo(String input) {
        try {
            String description = parseTodo(input);
            return new AddTodoCommand(description);
        } catch (EmptyFieldException e) {
            return new InvalidCommand(e.getMessage());

        }
    }

    public static Command createDeadline(String input) {
        String[] parts = parseDeadline(input);
        boolean isStringEmpty = parts[0].trim().isEmpty();
        if (parts.length < 2 || isStringEmpty) {
            return new InvalidCommand("Please use format: deadline <description> /by <date>");
        }
        String description = parts[0];
        String by = parts[1];
        try {
            LocalDateTime byAsDate = parseStringToDateTime(by);
            return new AddDeadlineCommand(description, byAsDate);
        } catch (DateTimeParseException e) {
            return new InvalidCommand("Please provide a valid date in yyyy-MM-dd HHmm format!");
        }
    }

    public static Command createEvent(String input) {
        String[] parts = parseEvent(input);
        boolean isStringEmpty = parts[0].trim().isEmpty();
        if (parts.length < 3 || isStringEmpty) {
            return new InvalidCommand("Please use format: event <description> /from <start> /to <end>");
        }
        String description = parts[0];
        String from = parts[1];
        String to = parts[2];
        try {
            LocalDateTime toAsDateTime = parseStringToDateTime(to);
            LocalDateTime fromAsDateTime = parseStringToDateTime(from);
            return new AddEventCommand(description, fromAsDateTime, toAsDateTime);
        } catch (DateTimeParseException e) {
            return new InvalidCommand("Please provide a valid date in yyyy-MM-dd HHmm format!");
        }
    }
}
