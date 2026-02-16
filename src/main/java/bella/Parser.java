package bella;

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
}
