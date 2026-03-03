package bella.commands;

import bella.storage.Storage;
import bella.tasks.Task;
import bella.tasks.TaskList;
import bella.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    String taskNum;
    public DeleteCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskIndex = Integer.parseInt(taskNum);
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
