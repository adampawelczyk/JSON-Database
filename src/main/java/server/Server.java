package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int port;
    private final ExecutorService executorService;
    private final ServerSocket serverSocket;
    private final CommandController commandController;

    public Server(int port) throws IOException {
        this.port = port;
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        serverSocket = new ServerSocket(port);
        commandController = new CommandController(new JsonDatabase());
    }

    public void run() {
        System.out.println("Server started!");
        try {
            while (true) {
                executorService.submit(new Session(serverSocket.accept(), commandController));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
