package client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {
    @Parameter(names = {"-t", "--type"})
    String type;
    @Parameter(names = {"-i", "--index"})
    int index;
    @Parameter(names = {"-m", "-message"})
    String message;

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 23456;

    public static void main(String ... argv) {
        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(argv);
        main.run();
    }

    public void run() {
        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
        ) {
            String sendMessage;

            if (message == null) {
                sendMessage = String.format("%s %d", type, index);
            } else {
                sendMessage = String.format("%s %d %s", type, index, message);
            }

            System.out.println("Client started!");
            outputStream.writeUTF(sendMessage);
            System.out.printf("Sent: %s\n", sendMessage);
            System.out.printf("Received: %s", dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}