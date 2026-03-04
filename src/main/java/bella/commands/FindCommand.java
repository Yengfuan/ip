package bella.commands;

import bella.storage.Storage;
import bella.tasks.TaskList;
import bella.ui.Ui;

/**
 * Represents a command that searches for tasks containing a specified keyword.
 */
public class FindCommand extends Command {
    String keyword;

    /**
     * Constructs a new {@code FindCommand} with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by searching the task list for tasks containing the keyword
     * and displaying the results.
     *
     * @param tasks   The {@link TaskList} to search through.
     * @param ui      The {@link Ui} used to display the matching tasks.
     * @param storage The {@link Storage} (unused).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList results = tasks.findByKeyword(keyword);
        if (results.isEmpty()) {
            ui.showError("Keyword not found!");
            return;
        }
        ui.showTaskList(results);
    }
}
