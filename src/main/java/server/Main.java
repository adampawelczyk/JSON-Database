package server;

import server.data.CommandController;
import server.data.JsonDatabase;
import server.data.JsonDatabaseArray;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final int PORT = 23456;

    public static void main(String[] args) {
        System.out.println("Server started!");
        JsonDatabase jsonDatabase = new JsonDatabaseArray(1000);
        CommandController commandController = new CommandController(jsonDatabase);

        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                try (
                        Socket socket = serverSocket.accept();
                        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
                ) {
                    String command = inputStream.readUTF();
                    String result = commandController.executeCommand(command);
                    outputStream.writeUTF(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}