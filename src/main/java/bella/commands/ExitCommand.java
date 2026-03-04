package bella.commands;

import bella.storage.Storage;
import bella.tasks.TaskList;
import bella.ui.Ui;

/**
 * Represents a command that exits the application.
 */
public class ExitCommand extends Command {

    /**
     * Returns {@code true} to signal the application to exit.
     *
     * @return {@code true} always.
     */
    @Override
    public boolean isExit() { return true; }

    /**
     * Executes the command by displaying a goodbye message to the user.
     *
     * @param tasks   The {@link TaskList} (unused).
     * @param ui      The {@link Ui} used to display the goodbye message.
     * @param storage The {@link Storage} (unused).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
