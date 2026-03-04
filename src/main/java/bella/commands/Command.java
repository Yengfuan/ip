package bella.commands;

import bella.storage.Storage;
import bella.tasks.TaskList;
import bella.ui.Ui;

/**
 * Represents an abstract command that can be executed by the application.
 *
 * <p>All supported commands (e.g., {@code AddTodoCommand}, {@code DeleteCommand}) extend this class
 * and provide their own implementation of {@link #execute(TaskList, Ui, Storage)}.</p>
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks   The {@link TaskList} to operate on.
     * @param ui      The {@link Ui} used to display messages to the user.
     * @param storage The {@link Storage} used to persist data.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns whether this command signals the application to exit.
     * Returns {@code false} by default; only {@code ExitCommand} overrides this.
     *
     * @return {@code true} if the application should exit; {@code false} otherwise.
     */
    public boolean isExit() { return false; }
}
