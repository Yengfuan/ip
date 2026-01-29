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

    public static void listTasks(String[] taskList, int taskCount) {
        if (taskCount != 0) {
            System.out.println(DIVIDER);
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + taskList[i]);
            }
            System.out.println(DIVIDER);
        } else {
            System.out.println(DIVIDER + "You haven't added anything yet! Woof!\n" + DIVIDER);
        }
    }

    public static void manageTask() {
        String line;
        String[] taskList = new String[100];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);

        System.out.println("What can I do for you?\n" + DIVIDER);

        line = in.nextLine();
        while (!line.equals("bye")) {
            String command = line;

            switch (command) {
            case "list":
                listTasks(taskList, taskCount);
                break;
            default:
                taskList[taskCount] = line;
                taskCount++;
                System.out.println(DIVIDER + "I've added: " + line + "\n" + DIVIDER);
            }

            line = in.nextLine();
        }
    }
    public static void main(String[] args) {
        greet();
        manageTask();
        System.out.println(DIVIDER + "Leaving the paw-ty? See you later!\n" + DIVIDER);
    }
}
