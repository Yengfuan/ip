package bella.commands;

import bella.storage.Storage;
import bella.tasks.TaskList;
import bella.ui.Ui;

public class InvalidCommand extends Command {
    String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(message);
    }
}
