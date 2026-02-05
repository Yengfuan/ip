public class Parser {

    public static String parseCommand(String input) {
        String[] parts = input.split(" ", 2);
        return parts[0];
    }

    public static String getArguments(String input) {
        String[] parts = input.split(" ", 2);
        return parts.length > 1 ? parts[1] : "";
    }

    public static int parseTaskNumber(String input) throws NumberFormatException {
        String args = getArguments(input);
        return Integer.parseInt(args);
    }

    public static String parseTodoDescription(String input) {
        return getArguments(input);
    }

    public static String[] parseDeadline(String input) {
        String args = getArguments(input);
        return args.split(" /by ", 2);
    }

    public static String[] parseEvent(String input) {
        String args = getArguments(input);
        return args.split(" /from | /to ");
    }
}
