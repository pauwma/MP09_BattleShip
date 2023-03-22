import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorHundirLaFlota {
    private static final int PUERTO = 8080;
    private static final int NUM_JUGADORES = 2;

    private static int[][] tableroJ1;
    private static int[][] tableroJ2;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado, esperando jugadores...");

            List<Socket> jugadores = new ArrayList<>();
            List<BufferedReader> lectores = new ArrayList<>();
            List<PrintWriter> escritores = new ArrayList<>();

            for (int i = 1; i <= NUM_JUGADORES; i++) {
                Socket socket = serverSocket.accept();
                jugadores.add(socket);
                lectores.add(new BufferedReader(new InputStreamReader(socket.getInputStream())));
                escritores.add(new PrintWriter(socket.getOutputStream(), true));
                System.out.println("Jugador " + i + " conectado. ("+socket.getInetAddress()+")");
            }

            // Iniciar juego
            for (PrintWriter escritor : escritores) {
                escritor.println("Iniciando juego");
            }

            // Recibir tableros de los jugadores
            int[][][] tableros = new int[NUM_JUGADORES][10][10];
            for (int i = 0; i < NUM_JUGADORES; i++) {
                String[] matrizSerializada = lectores.get(i).readLine().split("\n");
                for (int fila = 0; fila < 10; fila++) {
                    String[] elementos = matrizSerializada[fila].split(" ");
                    for (int columna = 0; columna < 10; columna++) {
                        tableros[i][fila][columna] = Integer.parseInt(elementos[columna]);
                    }
                }
            }

            // Turnos de juego
            Random random = new Random();
            int jugadorActual = random.nextInt(NUM_JUGADORES);
            boolean juegoTerminado = false;

            while (!juegoTerminado) {
                int otroJugador = (jugadorActual + 1) % NUM_JUGADORES;
                escritores.get(jugadorActual).println("Tu turno");
                escritores.get(otroJugador).println("Turno del otro jugador");

                // Lógica del juego y actualización de matrices

                // Comprobar si el juego ha terminado y actualizar el jugador actual
                jugadorActual = otroJugador;
            }

            // Enviar mensajes de fin de juego a los jugadores
        }
    }
}