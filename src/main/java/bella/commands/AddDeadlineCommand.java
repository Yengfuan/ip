package bella.commands;

import bella.storage.Storage;
import bella.tasks.Deadline;
import bella.tasks.Task;
import bella.tasks.TaskList;
import bella.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    String description;
    LocalDateTime by;

    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = new Deadline(description, by);
            tasks.add(task);
            ui.showTaskAdded(task, tasks.getCount());
            storage.writeToFile(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
