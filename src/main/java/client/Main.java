package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 23456;

    public static void main(String[] args) {
        String command = new CommandParser(args).toJson();
        System.out.println("Client started!");

        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())
        ) {
            dataOutputStream.writeUTF(command);
            System.out.printf("Send: %s\n", command);
            System.out.printf("Received: %s", dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}