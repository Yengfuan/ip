public class Bella {
    private TaskList tasks;
    private Ui ui;

    public Bella() {
        this.ui = new Ui();
        this.tasks = new TaskList();
    }

    public void run() {
        ui.showWelcome();

        String input = ui.readCommand();
        while (!input.equals("bye")) {
            handleCommand(input);
            input = ui.readCommand();
        }

        ui.showGoodbye();
    }

    private void handleCommand(String input) {
        if (input.trim().isEmpty()) {
            ui.showError("Cannot process empty description!");
            return;
        }
        String command = Parser.parseCommand(input);

        switch (command) {
        case "list":
            ui.showTaskList(tasks);
            break;
        case "mark":
            handleMark(input, true);
            break;
        case "unmark":
            handleMark(input, false);
            break;
        case "todo":
            handleTodo(input);
            break;
        case "deadline":
            handleDeadline(input);
            break;
        case "event":
            handleEvent(input);
            break;
        default:
            handleDefault(input);
            break;
        }
    }

    private void handleMark(String input, boolean isDone) {
        String args = Parser.getArguments(input);
        if (args.isEmpty()) {
            String action = isDone ? "mark" : "unmark";
            ui.showError("Please specify a task number to " + action);
            return;
        }

        try {
            int taskNum = Parser.parseTaskNumber(input);

            if (taskNum < 1 || taskNum > tasks.getCount()) {
                ui.showError("Task number out of range!");
                return;
            }

            Task task = tasks.get(taskNum - 1);

            if (isDone) {
                task.mark();
                ui.showMarkedDone(task);
            } else {
                task.unmark();
                ui.showMarkedUndone(task);
            }
        } catch (NumberFormatException e) {
            ui.showError("Please provide a valid task number!");
        }
    }

    private void handleTodo(String input) {
        try {
            String description = Parser.parseTodoDescription(input);
            Task task = new Todo(description);
            tasks.add(task);
            ui.showTaskAdded(task, tasks.getCount());
        } catch (EmptyFieldException e) {
            ui.showError(e.getMessage());
        }
    }

    private void handleDeadline(String input) {
        try {
            String args = Parser.getArguments(input);
            if (args.isEmpty()) {
                return;
            }

            String[] parts = Parser.parseDeadline(input);
            if (parts.length == 2) {
                Task task = new Deadline(parts[0], parts[1]);
                tasks.add(task);
                ui.showTaskAdded(task, tasks.getCount());
            } else {
                ui.showError("Please use format: deadline <description> /by <date>");
            }
        } catch (EmptyFieldException e) {
            ui.showError(e.getMessage());
        }
    }

    private void handleEvent(String input) {
        try {
            String args = Parser.getArguments(input);
            if (args.isEmpty()) {
                return;
            }

            String[] parts = Parser.parseEvent(input);
            if (parts.length == 3) {
                Task task = new Event(parts[0], parts[1], parts[2]);
                tasks.add(task);
                ui.showTaskAdded(task, tasks.getCount());
            } else {
                ui.showError("Please use format: event <description> /from <start> /to <end>");
            }
        } catch (EmptyFieldException e) {
            ui.showError(e.getMessage());
        }
    }

    private void handleDefault(String input) throws EmptyFieldException {
        try {
            System.out.println("Invalid command, try again!");
        } catch (EmptyFieldException e) {
            ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Bella().run();
    }
}
