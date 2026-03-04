package bella;

import bella.commands.*;
import bella.tasks.Deadline;
import bella.tasks.Event;
import bella.tasks.Task;
import bella.tasks.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Parser {
    public static int MAX_SPLIT_LENGTH = 2; //

    /**
     * Parses the full user input and returns the corresponding {@link Command} to execute.
     *
     * @param input Full raw input string entered by the user.
     * @return The {@link Command} object corresponding to the parsed command.
     */
    public static Command parse(String input) {
            String command = parseCommand(input);
            int taskIndex;
            switch (command) {
            case "todo": return CommandProducer.createTodo(input);
            case "deadline": return CommandProducer.createDeadline(input);
            case "event": return CommandProducer.createEvent(input);
            case "list": return new ListCommand();
            case "bye": return new ExitCommand();
            case "find":
                String keyword = getArguments(input);
                return new FindCommand(keyword);
            case "delete":
                taskIndex = parseTaskNumber(input);
                return new DeleteCommand(taskIndex);
            case "mark":
                taskIndex = parseTaskNumber(input);
                return new ToggleMarkStatusCommand(taskIndex, true);
            case "unmark":
                taskIndex = parseTaskNumber(input);
                return new ToggleMarkStatusCommand(taskIndex, false);
            default: return new InvalidCommand("Invalid command, try again!");
            }
    }

    /**
     * Extracts the arguments following the command keyword from the input string.
     *
     * @param input Full raw input string entered by the user.
     * @return The substring after the first word, or an empty string if none exists.
     */
    public static String getArguments(String input) {
        String[] parts = input.split(" ", MAX_SPLIT_LENGTH);
        return parts.length > 1 ? parts[1] : ""; // return second word and onwards
    }

    /**
     * Extracts the command keyword from the input string.
     *
     * @param input Full raw input string entered by the user.
     * @return The first word of the input string as the command keyword.
     */
    public static String parseCommand(String input) {
        String[] parts = input.split(" ", MAX_SPLIT_LENGTH);
        return parts[0];
    }

    /**
     * Parses a date-time string into a {@link LocalDateTime} object.
     *
     * @param dateTime Date-time string in {@code yyyy-MM-dd HHmm} format.
     * @return The parsed {@link LocalDateTime} object.
     * @throws DateTimeParseException If the string does not match the expected format.
     */
    public static LocalDateTime parseStringToDateTime(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Parses the task number from the input string.
     * Returns -1 if the argument is not a valid integer.
     *
     * @param input Full raw input string entered by the user.
     * @return The parsed task number, or -1 if parsing fails.
     */
    public static int parseTaskNumber(String input) throws NumberFormatException {
        String args = getArguments(input);
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    /**
     * Parses the todo description from the input string.
     *
     * @param input Full raw input string entered by the user.
     * @return The todo description string.
     * @throws EmptyFieldException If the description is null or empty.
     */
    public static String parseTodo(String input) throws EmptyFieldException {
        String description =  getArguments(input);
        if (description == null || description.trim().isEmpty()) {
            throw new EmptyFieldException("Cannot process empty description!");
        }
        return description;
    }

    /**
     * Splits the deadline input into description and date time components.
     *
     * @param input Full raw input string entered by the user.
     * @return A string array where index 0 is the description and index 1 is the date time.
     */
    public static String[] parseDeadline(String input) {
        String args = getArguments(input);
        return args.split(" /by ", MAX_SPLIT_LENGTH);
    }

    /**
     * Splits the event input into description, start, and end time components.
     *
     * @param input Full raw input string entered by the user.
     * @return A string array where index 0 is the description, index 1 is start, index 2 is end.
     */
    public static String[] parseEvent(String input) {
        String args = getArguments(input);
        return args.split(" /from | /to ");
    }

    /**
     * Parses a single line from the storage file into a {@link Task} object.
     * Returns null if the line is in an invalid format.
     *
     * @param line A single line read from the storage file.
     * @return The corresponding {@link Task} object, or null if the format is invalid.
     */
    public static Task parseTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            return null; //invalid format
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;

        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length >= 4) {
                task = new Deadline(description, parseStringToDateTime(parts[3]));
            }
            break;
        case "E":
            if (parts.length >= 5) {
                task = new Event(description,
                        parseStringToDateTime(parts[3]),
                        parseStringToDateTime(parts[4]));
                break;
            }
        }
        if (task != null && isDone) {
            task.mark();
        }
        return task;
    }
}
