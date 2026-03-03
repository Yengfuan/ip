package bella.commands;

import bella.storage.Storage;
import bella.tasks.TaskList;
import bella.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public boolean isExit() { return true; }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
