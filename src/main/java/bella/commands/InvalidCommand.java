package bella.commands;

import bella.storage.Storage;
import bella.tasks.TaskList;
import bella.ui.Ui;

/**
 * Represents a command that displays an error message for invalid or unrecognised input.
 */
public class InvalidCommand extends Command {
    String message;

    /**
     * Constructs a new {@code InvalidCommand} with the specified error message.
     *
     * @param message The error message to display to the user.
     */
    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the command by displaying the error message to the user.
     *
     * @param tasks   The {@link TaskList} (unused).
     * @param ui      The {@link Ui} used to display the error message.
     * @param storage The {@link Storage} (unused).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(message);
    }
}
