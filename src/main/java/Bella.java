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
        String description = Parser.parseTodoDescription(input);
        if (description.isEmpty()) {
            return;
        }
        Task task = new Todo(description);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.getCount());
    }

    private void handleDeadline(String input) {
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
    }

    private void handleEvent(String input) {
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
    }

    private void handleDefault(String input) {
        Task task = new Todo(input);
        tasks.add(task);
        ui.showError("I've added: " + input);
    }

    public static void main(String[] args) {
        new Bella().run();
    }
}
