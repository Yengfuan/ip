package bella.commands;

import bella.storage.Storage;
import bella.tasks.Event;
import bella.tasks.Task;
import bella.tasks.TaskList;
import bella.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static bella.Parser.parseStringToDateTime;

public class AddEventCommand extends Command {
    String description;
    LocalDateTime from;
    LocalDateTime to;

    public AddEventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = new Event(description, from, to);
            tasks.add(task);
            ui.showTaskAdded(task, tasks.getCount());
            storage.writeToFile(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
