package bella.commands;

import bella.storage.Storage;
import bella.tasks.TaskList;
import bella.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() { return false; }
}
