package bella.commands;

import bella.storage.Storage;
import bella.tasks.Task;
import bella.tasks.TaskList;
import bella.ui.Ui;

import java.io.IOException;

public class ToggleMarkStatusCommand extends Command {
    String taskNum;
    boolean isDone;

    public ToggleMarkStatusCommand(String taskNum, boolean isDone) {
        this.taskNum = taskNum;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskIndex = Integer.parseInt(taskNum);
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
        } catch (NumberFormatException e) {
            ui.showError("Please enter a number!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
