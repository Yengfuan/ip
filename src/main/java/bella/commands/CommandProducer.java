package bella.commands;

import bella.EmptyFieldException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static bella.Parser.*;

/**
 * Factory class responsible for creating {@link Command} objects from raw user input.
 *
 * <p>Each method handles parsing and validation for a specific command type,
 * returning an {@link InvalidCommand} if the input is malformed.</p>
 */
public class CommandProducer {

    /**
     * Creates an {@link AddTodoCommand} from the given input.
     *
     * @param input Full raw input string entered by the user.
     * @return An {@link AddTodoCommand} if valid, or an {@link InvalidCommand} if description is empty.
     */
    public static Command createTodo(String input) {
        try {
            String description = parseTodo(input);
            return new AddTodoCommand(description);
        } catch (EmptyFieldException e) {
            return new InvalidCommand(e.getMessage());

        }
    }

    /**
     * Creates an {@link AddDeadlineCommand} from the given input.
     *
     * @param input Full raw input string entered by the user.
     * @return An {@link AddDeadlineCommand} if valid, or an {@link InvalidCommand} if
     *         the format is incorrect or the date is invalid.
     */
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

    /**
     * Creates an {@link AddEventCommand} from the given input.
     *
     * @param input Full raw input string entered by the user.
     * @return An {@link AddEventCommand} if valid, or an {@link InvalidCommand} if
     *         the format is incorrect or the dates are invalid.
     */
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
