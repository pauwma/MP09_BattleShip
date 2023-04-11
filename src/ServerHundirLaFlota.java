import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerHundirLaFlota {

    private static final int PORT = 12345;
    private static final int MAX_USERS = 2;
    private static List<int[][]> matrices = new ArrayList<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_USERS);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);
            int connectedUsers = 0;
            List<ClientHandler> clients = new ArrayList<>();

            while (connectedUsers < MAX_USERS) {
                Socket socket = serverSocket.accept();
                connectedUsers++;
                System.out.println("Usuario conectado, esperando a otro usuario...");

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ClientHandler clientHandler = new ClientHandler(socket, out);
                clients.add(clientHandler);
                executorService.execute(clientHandler);

                if (connectedUsers == MAX_USERS) {
                    System.out.println("Los 2 usuarios están conectados. Enviando mensaje 'inicio'...");
                    for (ClientHandler client : clients) {
                        client.sendMessage("inicio");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;

        public ClientHandler(Socket socket, PrintWriter out) {
            this.socket = socket;
            this.out = out;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    String processedData = processData(inputLine);
                    System.out.println(processedData);
                }
            } catch (IOException e) {
                System.err.println("Error al comunicarse con el cliente: " + e.getMessage());
            } finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el socket: " + e.getMessage());
                }
            }
        }

        public void sendMessage(String message) {
            if (out != null) {
                out.println(message);
            }
        }
    }

    public static String processData(String data) {
        if (data.startsWith("MATRIZ")) {
            String[] lines = data.split("\n");
            int[][] matriz = new int[10][10];
            for (int i = 1; i < lines.length; i++) {
                String[] values = lines[i].split(" ");
                for (int j = 0; j < values.length; j++) {
                    matriz[i - 1][j] = Integer.parseInt(values[j]);
                }
            }
            matrices.add(matriz);
            System.out.println("Matriz recibida y almacenada.");

            // Imprimir la matriz
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    System.out.print(matriz[i][j] + " ");
                }
                System.out.println();
            }

            return "Matriz almacenada";
        } else {
            // Realizar el procesamiento de datos necesario aquí.
            // Este es solo un ejemplo básico de cómo devolver la información procesada.
            return "Datos procesados: " + data.toUpperCase();
        }
    }


}
