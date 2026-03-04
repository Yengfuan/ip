package bella.commands;

import bella.storage.Storage;
import bella.tasks.Task;
import bella.tasks.TaskList;
import bella.tasks.Todo;
import bella.ui.Ui;

import java.io.IOException;

/**
 * Represents a command that adds a {@link Todo} task to the task list.
 */
public class AddTodoCommand extends Command {
    String description;

    /**
     * Constructs a new {@code AddTodoCommand} with the specified description.
     *
     * @param description The description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by creating a {@link Todo} task, adding it to the list,
     * displaying a confirmation message, and saving to storage.
     *
     * @param tasks   The {@link TaskList} to add the task to.
     * @param ui      The {@link Ui} used to display the confirmation message.
     * @param storage The {@link Storage} used to persist the updated task list.
     * @throws RuntimeException If an error occurs while writing to storage.
     */
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
