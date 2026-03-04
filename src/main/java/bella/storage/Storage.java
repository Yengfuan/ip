package bella.storage;

import bella.tasks.*;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static bella.Parser.parseTaskFromLine;

/**
 * Handles reading and writing of {@link TaskList} data to a file.
 *
 * <p>Tasks are stored in a pipe-delimited format and loaded back into memory on startup.</p>
 */
public class Storage {
    private final File storageFile;

    /**
     * Constructs a new {@code Storage} with the specified file path.
     *
     * @param filePath Path to the file used for persistent storage.
     */
    public Storage(String filePath) {
        this.storageFile = new File(filePath);
    }

    /**
     * Writes all tasks in the given {@link TaskList} to the storage file.
     * Creates the necessary directories if they do not exist.
     *
     * @param tasks The {@link TaskList} to save.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void writeToFile(TaskList tasks) throws IOException {
        File directory = storageFile.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        FileWriter fw = new FileWriter(storageFile);
        for (int i = 0; i < tasks.getCount(); i++) {
            Task task = tasks.get(i);
            fw.write(taskToFileFormat(task));
            fw.write(System.lineSeparator());
        }

        fw.close();
    }

    /**
     * Loads tasks from the storage file into a {@link TaskList}.
     * Returns an empty list if the file does not exist.
     *
     * @return A {@link TaskList} populated with tasks from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public TaskList loadFromFile() throws IOException {
        TaskList tasks = new TaskList();

        if (!storageFile.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(storageFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTaskFromLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    /**
     * Converts a {@link Task} into a pipe-delimited string for file storage.
     *
     * @param task The {@link Task} to convert.
     * @return A formatted string representing the task, or an empty string if the type is unknown.
     */
    private String taskToFileFormat(Task task) {
        String type;
        String markStatus = task.getStatusIcon().equals("X") ? "1" : "0";
        String description = task.getDescription();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        if (task instanceof Todo) {
            type = "T";
            return type + " | " + markStatus + " | " + description;
        } else if (task instanceof Deadline d) {
            type = "D";
            return type + " | " + markStatus + " | " + description + " | " + d.getBy().format(formatter);
        } else if (task instanceof Event e) {
            type = "E";
            return type + " | " + markStatus + " | " + description + " | " +
                    e.getFrom().format(formatter) + " | " + e.getTo().format(formatter);
        }
        return "";
    }
}
