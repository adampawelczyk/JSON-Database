package server;

import server.data.CommandController;
import server.data.JsonDatabase;
import server.data.JsonDatabaseArray;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    private static final int PORT = 23456;

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        JsonDatabase jsonDatabase = new JsonDatabaseArray(100);
//        CommandController commandController = new CommandController(jsonDatabase);
//
//        String command = scanner.nextLine();
//        while (!command.equals("exit")) {
//            commandController.executeCommand(command);
//
//            command = scanner.nextLine();
//        }

        System.out.println("Server started!");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            try (
                    Socket socket = serverSocket.accept();
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
            ) {
                String message = inputStream.readUTF();
                System.out.printf("Received: %s\n", message);

                String sendMessage = "A record # 12 was sent!";
                outputStream.writeUTF(sendMessage);
                System.out.printf("Sent: %s", sendMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}