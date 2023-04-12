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
    private static JuegoController juego;
    private static List<int[][]> matrices = new ArrayList<>();
    private static List<ClientHandler> clients = new ArrayList<>();


    public static void main(String[] args) {
        juego = new JuegoController(); // ? Inicializar el objeto JuegoController
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_USERS);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);
            int connectedUsers = 0;

            while (connectedUsers < MAX_USERS) {
                Socket socket = serverSocket.accept();
                connectedUsers++;
                if (connectedUsers == 1){
                    System.out.println("J1 conectado, esperando a otro usuario...");
                } else if (connectedUsers == 2){
                    System.out.println("J2 conectado");
                }

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ClientHandler clientHandler = new ClientHandler(socket, out);
                clients.add(clientHandler);
                executorService.execute(clientHandler);

                if (connectedUsers == MAX_USERS) {
                    System.out.println("Iniciando juego, enviando mensaje 'inicio'...");
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
        if (data.startsWith("MATRIZ-")) {
            String matrixData = data.substring(7); // ? Eliminar "MATRIZ-" del comienzo
            int[][] matriz = new int[10][10];
            int index = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    matriz[i][j] = Character.getNumericValue(matrixData.charAt(index));
                    index++;
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

            // Guardar los tableros en el controlador de juego
            if (matrices.size() == 1) {
                juego.setTableroJ1(matriz);
                System.out.println("Tablero del jugador 1 almacenado.");
            } else if (matrices.size() == 2) {
                juego.setTableroJ2(matriz);
                System.out.println("Tablero del jugador 2 almacenado.");

                // Verificar si ambos tableros están almacenados y enviar el mensaje "juego"
                if (juego.getTableroJ1() != null && juego.getTableroJ2() != null) {
                    System.out.println("Ambos tableros almacenados. Iniciando el juego...");
                    for (ClientHandler client : clients) {
                        client.sendMessage("juego");
                    }
                }
            }

            return "Matriz almacenada";
        } else {
            // Realizar el procesamiento de datos necesario aquí.
            // Este es solo un ejemplo básico de cómo devolver la información procesada.
            return "Datos procesados: " + data.toUpperCase();
        }
    }
}