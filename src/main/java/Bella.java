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

    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("What can I do for you?\n" + DIVIDER);
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(DIVIDER + "Woof " + line + " woof\n" + DIVIDER);
            line = in.nextLine();
        }
    }
    public static void main(String[] args) {
        greet();
        echo();
        System.out.println(DIVIDER + "Leaving the paw-ty? See you later!\n" + DIVIDER);
    }
}
