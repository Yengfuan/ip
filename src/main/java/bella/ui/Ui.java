package bella.ui;

import bella.tasks.Task;
import bella.tasks.TaskList;

import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________\n";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = "   __\n" +
                "o-''|\\_____/)\n" +
                " \\_/|_)     )\n" +
                "    \\  __  /\n" +
                "    (_/ (_/";
        System.out.println(DIVIDER + "Howl you doing I'm Bella, your personal chatbot!\n" + logo);
        System.out.println("What can I do for you?\n" + DIVIDER);
    }

    public void showGoodbye() {
        System.out.println(DIVIDER + "Leaving the paw-ty? See you later!\n" + DIVIDER);
    }

    public void showTaskList(TaskList tasks) {
        if (!tasks.isEmpty()) {
            System.out.println(DIVIDER);
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.getCount(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
            }
            System.out.println(DIVIDER);
        } else {
            System.out.println(DIVIDER + "You haven't added anything yet! Woof!\n" + DIVIDER);
        }
    }

    public void showTaskAdded(Task task, int count) {
        System.out.println(DIVIDER + " Got it. I've added this task:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public void showMarkedDone(Task task) {
        System.out.println(DIVIDER + " Nice! I've marked this task as done:");
        System.out.println("   " + task.toString());
        System.out.println(DIVIDER);
    }

    public void showMarkedUndone(Task task) {
        System.out.println(DIVIDER + " OK, I've marked this task as not done yet:");
        System.out.println("   " + task.toString());
        System.out.println(DIVIDER);
    }

    public void showError(String message) {
        System.out.println(DIVIDER + message + "\n" + DIVIDER);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
