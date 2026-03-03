package bella.commands;

import bella.storage.Storage;
import bella.tasks.Task;
import bella.tasks.TaskList;
import bella.ui.Ui;

import java.io.IOException;

public class ToggleMarkStatusCommand extends Command {
    int taskIndex;
    boolean isDone;

    public ToggleMarkStatusCommand(int taskIndex, boolean isDone) {
        this.taskIndex = taskIndex;
        this.isDone = isDone;
    }

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
