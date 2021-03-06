package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 23456;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
        ) {
            String message = "Give me a record # 12";

            System.out.println("Client started!");
            outputStream.writeUTF(message);
            System.out.printf("Sent: %s\n", message);
            System.out.printf("Received: %s", dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
