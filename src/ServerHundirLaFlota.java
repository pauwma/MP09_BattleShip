import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

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
                if (connectedUsers == 1) {
                    System.out.println("J1 conectado, esperando a otro usuario...");
                } else if (connectedUsers == 2) {
                    System.out.println("J2 conectado");
                }

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ClientHandler clientHandler = new ClientHandler(socket, out);
                clients.add(clientHandler);
                executorService.execute(clientHandler);

                if (connectedUsers == MAX_USERS) {
                    System.out.println("Iniciando juego...");
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
        private String response;

        public ClientHandler(Socket socket, PrintWriter out) {
            this.socket = socket;
            this.out = out;
        }

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    String processedData = processData(inputLine);
                    System.out.println(processedData);
                    setResponse(processedData);
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

            // ? Guardar los tableros en el controlador de juego
            int tmpJugador = 0;
            if (matrices.size() == 1) {
                juego.setTableroJ1(matriz);
                tmpJugador = 1;
            } else if (matrices.size() == 2) {
                juego.setTableroJ2(matriz);
                tmpJugador = 2;
                // Verificar si ambos tableros están almacenados y comenzar el juego
                if (juego.getTableroJ1() != null && juego.getTableroJ2() != null) {
                    System.out.println("Ambos tableros almacenados. Iniciando el juego...");
                    startGameLoop();
                }
            }
            return "Tablero del jugador " + tmpJugador + " almacenado.";
        } else {
            // Realizar el procesamiento de datos necesario aquí.
            // Este es solo un ejemplo básico de cómo devolver la información procesada.
            return "Datos procesados: " + data.toUpperCase();
        }
    }

    public static void startGameLoop() {
        Random random = new Random();
        boolean randomTurn = random.nextBoolean();

        // Asignar turno inicial aleatoriamente
        juego.setTurno(randomTurn);
        clients.get(0).sendMessage(randomTurn ? "turno" : "espera");
        clients.get(1).sendMessage(randomTurn ? "espera" : "turno");

        new Thread(() -> gameLoop()).start();
    }

    public static void gameLoop() {
        AtomicBoolean validResponse = new AtomicBoolean(false);

        while (!juego.isGameOver()) {
            int currentPlayer = juego.isTurno() ? 0 : 1;
            validResponse.set(false);

            while (!validResponse.get()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String playerResponse = clients.get(currentPlayer).getResponse();
                if (playerResponse != null && playerResponse.startsWith("POS-")) {
                    validResponse.set(juego.procesarPosicion(playerResponse));
                    clients.get(currentPlayer).setResponse(null);
                }
            }

            // Cambiar el turno
            juego.nextTurno();
            clients.get(0).sendMessage(juego.isTurno() ? "turno" : "espera");
            clients.get(1).sendMessage(juego.isTurno() ? "espera" : "turno");
        }
    }
}