package bella.commands;

import bella.storage.Storage;
import bella.tasks.Task;
import bella.tasks.TaskList;
import bella.ui.Ui;

import java.io.IOException;

/**
 * Represents a command that deletes a task from the task list by its 1-based index.
 */
public class DeleteCommand extends Command {
    int taskIndex;

    /**
     * Constructs a new {@code DeleteCommand} with the specified task index.
     *
     * @param taskIndex The 1-based index of the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by removing the task at the specified index from the list,
     * displaying a confirmation message, and saving to storage.
     *
     * @param tasks   The {@link TaskList} to delete the task from.
     * @param ui      The {@link Ui} used to display messages to the user.
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
            int deletedTaskIndex = taskIndex - 1;
            Task deletedTask = tasks.get(deletedTaskIndex);
            tasks.delete(deletedTaskIndex);
            ui.showDeleted(deletedTask, tasks.getCount());
            storage.writeToFile(tasks);
        } catch (NumberFormatException e) {
            ui.showError("Please enter a number!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
