package bella.storage;

import bella.tasks.*;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import static bella.Parser.parseTaskFromLine;

public class StorageFile {
    private final File storageFile;

    public StorageFile(String filePath) {
        this.storageFile = new File(filePath);
    }

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

    private String taskToFileFormat(Task task) {
        String type;
        String markStatus = task.getStatusIcon().equals("X") ? "1" : "0";
        String description = task.getDescription();

        if (task instanceof Todo) {
            type = "T";
            return type + " | " + markStatus + " | " + description;
        } else if (task instanceof Deadline d) {
            type = "D";
            return type + " | " + markStatus + " | " + description + " | " + d.getBy();
        } else if (task instanceof Event e) {
            type = "E";
            return type + " | " + markStatus + " | " + description + " | " + e.getFrom() + " | " + e.getTo();
        }
        return "";
    }
}
