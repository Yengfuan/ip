package bella.commands;

import bella.storage.Storage;
import bella.tasks.Task;
import bella.tasks.TaskList;
import bella.ui.Ui;

import java.io.IOException;

/**
 * Represents a command that marks or unmarks a task as done based on its 1-based index.
 */
public class ToggleMarkStatusCommand extends Command {
    int taskIndex;
    boolean isDone;

    /**
     * Constructs a new {@code ToggleMarkStatusCommand} with the specified task index and status.
     *
     * @param taskIndex The 1-based index of the task to mark or unmark.
     * @param isDone    {@code true} to mark the task as done; {@code false} to mark it as not done.
     */
    public ToggleMarkStatusCommand(int taskIndex, boolean isDone) {
        this.taskIndex = taskIndex;
        this.isDone = isDone;
    }

    /**
     * Executes the command by marking or unmarking the task at the specified index,
     * displaying a confirmation message, and saving to storage.
     *
     * @param tasks   The {@link TaskList} containing the task to update.
     * @param ui      The {@link Ui} used to display the confirmation message.
     * @param storage The {@link Storage} used to persist the updated task list.
     * @throws RuntimeException If an error occurs while writing to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (taskIndex < 1 || taskIndex > tasks.getCount()) {
                ui.showError("Task number out of range!");
                return;
            }

            Task task = tasks.get(taskIndex - 1);

            if (isDone) {
                task.mark();
                ui.showMarkedDone(task);
            } else {
                task.unmark();
                ui.showMarkedUndone(task);
            }
            storage.writeToFile(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
