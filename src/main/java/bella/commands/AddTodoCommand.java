package bella.commands;

import bella.storage.Storage;
import bella.tasks.Task;
import bella.tasks.TaskList;
import bella.tasks.Todo;
import bella.ui.Ui;

import java.io.IOException;

public class AddTodoCommand extends Command {
    String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = new Todo(description);
            tasks.add(task);
            ui.showTaskAdded(task, tasks.getCount());
            storage.writeToFile(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
