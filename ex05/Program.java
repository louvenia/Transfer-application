package ex05;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        if(args.length == 0 || !args[0].startsWith("--profile=")) {
            System.err.println("Enter application build mode. For example: --profile=dev or --profile=production");
            input.close();
            System.exit(-1);
        } else {
            String launch = args[0].replace("--profile=", "");
            int numberPosition;
            Menu menu = new Menu();
            menu.printInterface(launch);
            if(input.hasNextInt()) {
                numberPosition = input.nextInt();
                while((numberPosition != 7 && launch.equals("dev")) || (numberPosition != 5 && launch.equals("production"))) {
                    menu.chooseNumber(numberPosition);
                    menu.printInterface(launch);
                    numberPosition = input.nextInt();
                }
            }
        }
        input.close();
    }
}
