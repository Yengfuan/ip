package bella.commands;

import bella.storage.Storage;
import bella.tasks.TaskList;
import bella.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
