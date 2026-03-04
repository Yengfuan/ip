package bella.commands;

import bella.storage.Storage;
import bella.tasks.Deadline;
import bella.tasks.Task;
import bella.tasks.TaskList;
import bella.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Represents a command that adds a {@link Deadline} task to the task list.
 */
public class AddDeadlineCommand extends Command {
    String description;
    LocalDateTime by;

    /**
     * Constructs a new {@code AddDeadlineCommand} with the specified description and due date-time.
     *
     * @param description The description of the deadline task.
     * @param by The due date and time of the deadline.
     */
    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command by creating a {@link Deadline} task, adding it to the list,
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
            Task task = new Deadline(description, by);
            tasks.add(task);
            ui.showTaskAdded(task, tasks.getCount());
            storage.writeToFile(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
