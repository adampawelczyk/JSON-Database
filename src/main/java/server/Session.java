package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Session implements Runnable {
    private final Socket socket;
    private final CommandController commandController;

    public Session(Socket socket, CommandController commandController) {
        this.socket = socket;
        this.commandController = commandController;
    }

    @Override
    public void run() {
        try (
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
        ) {
            String jsonCommand = inputStream.readUTF();
            Command command = CommandParser.parse(jsonCommand);


            if (command.getType().equals("exit")) {
                String responseJson = "{ \"response\": \"OK\" }";
                outputStream.writeUTF(responseJson);
                System.exit(0);
            }

            String result = commandController.executeCommand(command);
            outputStream.writeUTF(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
