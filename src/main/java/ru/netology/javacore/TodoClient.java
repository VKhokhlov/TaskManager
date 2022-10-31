package ru.netology.javacore;

import java.io.*;
import java.net.Socket;

public class TodoClient {
    private static final int PORT = 8989;
    private static final String HOST = "localhost";
    private static final String FILE = "requests.txt";

    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader(FILE))
        ) {
            in.lines().forEach(TodoClient::send);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void send(String line) {
        if (line.isEmpty() || line.startsWith("#")) {
            return;
        }

        try (Socket clientSocket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            System.out.println("Request:\n" + line);
            out.println(line);
            System.out.println("Response:\n" + in.readLine() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
