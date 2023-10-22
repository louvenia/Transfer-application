package ex05;

public class Program {
    private static void errorMessage() {
        System.err.println("Enter application build mode. For example: --profile=dev or --profile=production");
        System.exit(-1);
    }

    public static void main(String[] args) {
        if(args.length != 1 || !args[0].startsWith("--profile=")) {
            errorMessage();
        } else {
            String launch = args[0].replace("--profile=", "");
            if(launch.equals("dev") || launch.equals("production")) {
                Menu menu = new Menu();
                menu.generalWindow(launch);
            } else {
                errorMessage();
            }
        }
    }
}