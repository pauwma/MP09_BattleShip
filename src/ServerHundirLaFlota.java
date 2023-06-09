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
import java.util.concurrent.atomic.AtomicReference;

public class ServerHundirLaFlota {

    private static int PORT = 12345;
    private static final int MAX_USERS = 2;
    private static JuegoController juego;
    private static List<int[][]> matrices = new ArrayList<>();
    private static List<ClientHandler> clients = new ArrayList<>();
    private static boolean logEnabled = false; // Cambia a true para habilitar los mensajes de registro

    public ServerHundirLaFlota(int puerto){
        PORT = puerto;
    }

    public ServerHundirLaFlota(){}

    public void inicio() {
        juego = new JuegoController(); // ? Inicializar el objeto JuegoController
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_USERS);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            log("Servidor iniciado en el puerto " + PORT);
            int connectedUsers = 0;

            while (connectedUsers < MAX_USERS) {
                Socket socket = serverSocket.accept();
                connectedUsers++;
                if (connectedUsers == 1) {
                    log("J1 conectado, esperando a otro usuario...");
                } else if (connectedUsers == 2) {
                    log("J2 conectado");
                }

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ClientHandler clientHandler = new ClientHandler(socket, out);
                clients.add(clientHandler);
                executorService.execute(clientHandler);

                if (connectedUsers == MAX_USERS) {
                    log("Iniciando juego...");
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
                    if(processedData !=null){
                        log(processedData);
                    }
                    synchronized (this) {
                        setResponse(processedData);
                        notify();
                    }
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

        public synchronized String waitForResponse() {
            while (response == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String result = response;
            response = null;
            return result;
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
                    log("Ambos tableros almacenados. Iniciando el juego...");
                    startGameLoop();
                }
            }
            return null;
        } else if (data.startsWith("POS-")){
            String posData = data.substring(4); // ? Eliminar "POS-" del comienzo
            return posData;
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
        juego.setTurno(!randomTurn); // Cambiar el turno en el controlador de juego


        new Thread(() -> {
            try {
                gameLoop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void gameLoop() throws InterruptedException {
        juego.randomTurno();

        while (!juego.isGameOver()) {

            int currentPlayer = juego.isTurno() ? 0 : 1;
            String playerResponse = clients.get(currentPlayer).waitForResponse();

            if (playerResponse != null && playerResponse.length() == 2) {
                boolean validMove = juego.procesarPosicion(playerResponse);

                // ? Enviar ganador
                if (juego.comprobarGanador()){
                    clients.get(0).sendMessage(juego.isTurno() ? "ganador" : "perdedor");
                    clients.get(1).sendMessage(juego.isTurno() ? "perdedor" : "ganador");
                } else {
                    if (validMove) {
                        // Cambiar el turno solo si el movimiento es válido
                        juego.nextTurno();

                        // ? Devolver tablas
                        String tableros = juego.matricesToString(juego.getTableroJ1(), juego.getTableroJ2());
                        clients.get(0).sendMessage(tableros);
                        clients.get(1).sendMessage(tableros);

                        // ? Enviar turnos
                        clients.get(0).sendMessage(juego.isTurno() ? "turno" : "espera");
                        clients.get(1).sendMessage(juego.isTurno() ? "espera" : "turno");
                        Thread.sleep(1000);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ServerHundirLaFlota serverHundirLaFlota = new ServerHundirLaFlota();
        serverHundirLaFlota.inicio();
    }

    private static void log(String message) {
        if (logEnabled) {
            log(message);
        }
    }

}