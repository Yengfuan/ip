package bella;

import bella.tasks.Deadline;
import bella.tasks.Event;
import bella.tasks.Task;
import bella.tasks.Todo;

public class Parser {
    public static int MAX_SPLIT_LENGTH = 2; //

    public static String parseCommand(String input) {
        String[] parts = input.split(" ", MAX_SPLIT_LENGTH);
        return parts[0];
    }

    public static String getArguments(String input) {
        String[] parts = input.split(" ", MAX_SPLIT_LENGTH);
        return parts.length > 1 ? parts[1] : ""; // return second word and onwards
    }

    public static int parseTaskNumber(String input) throws NumberFormatException {
        String args = getArguments(input);
        return Integer.parseInt(args);
    }

    public static String parseTodoDescription(String input) throws EmptyFieldException {
        String description =  getArguments(input);
        if (description == null || description.trim().isEmpty()) {
            throw new EmptyFieldException("Cannot process empty description!");
        }
        return description;
    }

    public static String[] parseDeadline(String input) {
        String args = getArguments(input);
        return args.split(" /by ", MAX_SPLIT_LENGTH);
    }

    public static String[] parseEvent(String input) {
        String args = getArguments(input);
        return args.split(" /from | /to ");
    }

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
                task = new Deadline(description, parts[3]);
            }
            break;
        case "E":
            if (parts.length >= 5) {
                task = new Event(description, parts[3], parts[4]);
                break;
            }
        }
        if (task != null & isDone) {
            task.mark();
        }
        return task;
    }
}
