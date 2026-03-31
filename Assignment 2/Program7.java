// ──────────────────────────────────────────────────────────────
// Q7: TCP Echo Client–Server Program
//
// HOW TO RUN:
//   Terminal 1:  java Q7_TCPEchoServer
//   Terminal 2:  java Q7_TCPEchoClientServer   (runs client automatically)
//
// Both classes are in this file for convenience.
// In practice, compile and run each separately.
// ──────────────────────────────────────────────────────────────

import java.io.*;
import java.net.*;

// ── Server ────────────────────────────────────────────────────
class EchoServer implements Runnable {
    private final int port;

    EchoServer(int port) { this.port = port; }

    @Override
    public void run() {
        System.out.println("[Server] Starting on port " + port + "…");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[Server] Waiting for client…");

            // Accept one client at a time (loop for multiple clients)
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("[Server] Client connected: "
                        + clientSocket.getInetAddress());

                // Handle client in a new thread so server stays responsive
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("[Server] Stopped: " + e.getMessage());
        }
    }

    private void handleClient(Socket socket) {
        try (
            BufferedReader  in  = new BufferedReader(
                                    new InputStreamReader(socket.getInputStream()));
            PrintWriter     out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("[Server] Received : " + line);
                if (line.equalsIgnoreCase("bye")) {
                    out.println("Goodbye!");
                    break;
                }
                out.println("Echo: " + line);   // echo back
            }
        } catch (IOException e) {
            System.out.println("[Server] Client error: " + e.getMessage());
        } finally {
            try { socket.close(); } catch (IOException ignored) {}
            System.out.println("[Server] Client disconnected.");
        }
    }
}

// ── Client ────────────────────────────────────────────────────
class EchoClient {
    private final String host;
    private final int    port;

    EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    void start() {
        System.out.println("[Client] Connecting to " + host + ":" + port);

        try (
            Socket         socket = new Socket(host, port);
            BufferedReader in     = new BufferedReader(
                                     new InputStreamReader(socket.getInputStream()));
            PrintWriter    out    = new PrintWriter(socket.getOutputStream(), true)
        ) {
            System.out.println("[Client] Connected!\n");

            String[] messages = {"Hello", "How are you?", "Java is fun!", "bye"};

            for (String msg : messages) {
                System.out.println("[Client] Sending  : " + msg);
                out.println(msg);

                String response = in.readLine();
                System.out.println("[Client] Received : " + response);
                System.out.println();

                Thread.sleep(300);             // small delay for readability

                if (msg.equalsIgnoreCase("bye")) break;
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("[Client] Error: " + e.getMessage());
        }
    }
}

// ── Main ──────────────────────────────────────────────────────
public class Q7_TCPEchoClientServer {
    public static void main(String[] args) throws InterruptedException {
        final int PORT = 9090;

        // Start server in a background thread
        Thread serverThread = new Thread(new EchoServer(PORT));
        serverThread.setDaemon(true);          // exits when main exits
        serverThread.start();

        Thread.sleep(500);                     // give server time to bind

        // Run client on main thread
        EchoClient client = new EchoClient("localhost", PORT);
        client.start();

        System.out.println("[Main] Demo complete.");
    }
}
