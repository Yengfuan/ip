package bella.commands;

import bella.storage.Storage;
import bella.tasks.TaskList;
import bella.ui.Ui;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList results = tasks.findByKeyword(keyword);
        if (results.isEmpty()) {
            ui.showError("Keyword not found!");
            return;
        }
        for (int i = 0; i < results.getCount(); i++) {
            ui.showTaskList(results);
        }
    }
}
