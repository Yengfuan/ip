package bella.commands;

import static bella.Parser.*;

public class CommandProducer {
    public static Command createTodo(String input) {
        String description = parseTodo(input);
        if (description.trim().isEmpty()) {
            return new InvalidCommand("Please provide a description for todo!");
        }
        return new AddTodoCommand(description);
    }

    public static Command createDeadline(String input) {
        String[] parts = parseDeadline(input);
        boolean isStringEmpty = parts[0].trim().isEmpty();
        if (parts.length < 2 || isStringEmpty) {
            return new InvalidCommand("Please use format: deadline <description> /by <date>");
        }
        String description = parts[0];
        String by = parts[1];
        return new AddDeadlineCommand(description, by);
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
        return new AddEventCommand(description, from, to);
    }
}
