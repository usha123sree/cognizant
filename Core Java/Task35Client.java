package cognizant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Task35Client {

    private static final String SERVER_IP = "127.0.0.1"; 
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Connecting to server...");

        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to server. You can start chatting. Type 'bye' to exit.");

            String clientMessage;
            String serverMessage;

            Thread readThread = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println("Server: " + msg);
                        if (msg.equalsIgnoreCase("bye")) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error reading from server: " + e.getMessage());
                } finally {
                    System.out.println("Server disconnected or chat ended.");
                }
            });
            readThread.start();

            while (true) {
                clientMessage = clientInput.readLine();
                out.println(clientMessage);
                if (clientMessage.equalsIgnoreCase("bye")) {
                    break;
                }
            }
            readThread.join();

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
        System.out.println("Client disconnected.");
    }
}