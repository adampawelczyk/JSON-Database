package server;

import server.data.CommandController;
import server.data.JsonDatabase;
import server.data.JsonDatabaseArray;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JsonDatabase jsonDatabase = new JsonDatabaseArray(100);
        CommandController commandController = new CommandController(jsonDatabase);

        String command = scanner.nextLine();
        while (!command.equals("exit")) {
            commandController.executeCommand(command);

            command = scanner.nextLine();
        }
    }
}