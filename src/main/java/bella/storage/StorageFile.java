package bella.storage;

import bella.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Storage {
    private final Path filePath;

    public Storage(String filepath) {
        this.filePath = Paths.get(filepath);
    }

    public void save(TaskList tasks) throws IOException {
        Files.createDirectories(filePath.getParent());
        Files.writeString(filePath, convertTasksToString(tasks));
    }

    public void load() throws IOException {
        if (Files.exists(filePath)) {
            // Read and parse tasks
        }
    }

    public String convertTasksToString(TaskList tasks) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.getCount(); i++) {
            Task task = tasks.get(i);
            sb.append(taskToFileFormat(task));
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    private String taskToFileFormat(Task task) {
        String type;
        String markStatus = task.getStatusIcon().equals("X") ? "1" : "0";
        String description = task.getDescription();

        if (task instanceof Todo) {
            type = "T";
            return type + " | " + markStatus + " | " + description;
        } else if (task instanceof Deadline) {
            type = "D";
            return type + " | " + markStatus + " | " + description;
        } else if (task instanceof Event) {
            type = "E";
            return type + " | " + markStatus + " | " + description;
        }
        return "";
    }
}
