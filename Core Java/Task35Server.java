package cognizant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Task35Server {

    private static final int PORT = 12345;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Server is starting...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server listening on port " + PORT);

            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in))) {

                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                System.out.println("You can start chatting. Type 'bye' to exit.");

                String clientMessage;
                String serverMessage;

                Thread readThread = new Thread(() -> {
                    try {
                        String msg;
                        while ((msg = in.readLine()) != null) {
                            System.out.println("Client: " + msg);
                            if (msg.equalsIgnoreCase("bye")) {
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading from client: " + e.getMessage());
                    } finally {
                        System.out.println("Client disconnected or chat ended.");
                    }
                });
                readThread.start();

                while (true) {
                    serverMessage = serverInput.readLine();
                    out.println(serverMessage);
                    if (serverMessage.equalsIgnoreCase("bye")) {
                        break;
                    }
                }
                readThread.join(); 

            } catch (IOException e) {
                System.err.println("Error in server communication: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Could not listen on port " + PORT + ": " + e.getMessage());
        }
        System.out.println("Server stopped.");
    }
}