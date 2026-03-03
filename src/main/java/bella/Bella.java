package bella;

import bella.commands.Command;
import bella.storage.Storage;
import bella.tasks.*;
import bella.ui.Ui;

import java.io.IOException;

public class Bella {
    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;
    private static final String FILE_PATH = "./data/bella.txt";

    public Bella() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        try {
            this.tasks = storage.loadFromFile();
        } catch (IOException e) {
            ui.showError("Error loading tasks, let's start with an empty list!");
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        new Bella().run();
    }
}
