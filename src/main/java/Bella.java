import java.util.Scanner;

public class Bella {
    private static final String DIVIDER = "____________________________________________________________\n";
    public static void greet() {
        String logo = "   __\n" +
                "o-''|\\_____/)\n" +
                " \\_/|_)     )\n" +
                "    \\  __  /\n" +
                "    (_/ (_/";
        System.out.println(DIVIDER + "Howl you doing I'm Bella, your personal chatbot!\n" + logo);
    }

    public static void listTasks(Task[] taskList, int taskCount) {
        if (taskCount != 0) {
            System.out.println(DIVIDER);
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + taskList[i].description + " [" + taskList[i].getStatusIcon() + "]");
            }
            System.out.println(DIVIDER);
        } else {
            System.out.println(DIVIDER + "You haven't added anything yet! Woof!\n" + DIVIDER);
        }
    }

    public static void manageTask() {
        Task[] taskList = new Task[100];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);

        System.out.println("What can I do for you?\n" + DIVIDER);

        String line = in.nextLine();
        while (!line.equals("bye")) {
            String[] parts = line.split(" ", 2);
            String command = parts[0];

            switch (command) {
            case "list":
                System.out.println(DIVIDER + "Here are the tasks in your list:\n");
                listTasks(taskList, taskCount);
                break;
            case "mark":
                if (parts.length > 1) {
                    handleMarkTask(taskList, taskCount, parts[1], true);
                } else {
                    System.out.println(DIVIDER + "Please specify a task number to mark\n" + DIVIDER);
                }
                break;
            case "unmark":
                if (parts.length > 1) {
                    handleMarkTask(taskList, taskCount, parts[1], false);
                } else {
                    System.out.println(DIVIDER + "Please specify a task number to mark\n" + DIVIDER);
                }
                break;
            default:
                taskList[taskCount] = new Task(line);
                taskCount++;
                System.out.println(DIVIDER + "I've added: " + line + "\n" + DIVIDER);
            }

            line = in.nextLine();
        }
    }

    private static void handleMarkTask(Task[] taskList, int taskCount, String taskNumStr, boolean isDone) {
        try {
            int taskNum = Integer.parseInt(taskNumStr);

            if (taskNum < 1 || taskNum > taskCount) {
                System.out.println(DIVIDER + "Task number out of range!\n" + DIVIDER);
                return;
            }

            Task task = taskList[taskNum - 1];

            if (isDone) {
                task.mark();
                System.out.println(DIVIDER + " Nice! I've marked this task as done:");
            } else {
                task.unmark();
                System.out.println(DIVIDER + " OK, I've marked this task as not done yet:");
            }
            System.out.println("   [" + task.getStatusIcon() + "] " + task.getDescription());
            System.out.println(DIVIDER);

        } catch (NumberFormatException e) {
            System.out.println(DIVIDER + "Please provide a valid task number!\n" + DIVIDER);
        }
    }

    public static void main(String[] args) {
        greet();
        manageTask();
        System.out.println(DIVIDER + "Leaving the paw-ty? See you later!\n" + DIVIDER);
    }
}
