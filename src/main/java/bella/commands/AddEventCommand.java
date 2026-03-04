package bella.commands;

import bella.storage.Storage;
import bella.tasks.Event;
import bella.tasks.Task;
import bella.tasks.TaskList;
import bella.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Represents a command that adds a {@link Event} task to the task list.
 */
public class AddEventCommand extends Command {
    String description;
    LocalDateTime from;
    LocalDateTime to;

    /**
     * Constructs a new {@code AddEventCommand} with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public AddEventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command by creating an {@link Event} task, adding it to the list,
     * displaying a confirmation message, and saving to storage.
     *
     * @param tasks   The {@link TaskList} to add the task to.
     * @param ui      The {@link Ui} used to display the confirmation message.
     * @param storage The {@link Storage} used to persist the updated task list.
     * @throws RuntimeException If an error occurs while writing to storage.
     */
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
