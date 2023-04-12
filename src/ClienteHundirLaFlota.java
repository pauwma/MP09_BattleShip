import com.sun.tools.javac.Main;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteHundirLaFlota {
    private static final String HOST = "localhost";
    private static final int PUERTO = 12345;
    public static void main(String[] args) throws IOException {

        try (Socket socket = new Socket(HOST, PUERTO)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String mensajeDelServidor;
            while ((mensajeDelServidor = in.readLine()) != null) {
                System.out.println(mensajeDelServidor);

                if (mensajeDelServidor.startsWith("inicio")) {
                    Tablero tablero = new Tablero();
                    System.out.println("Coloca tus barcos:");
                    tablero.colocarBarcos();
                    int[][] matriz = tablero.getTablero();

                    StringBuilder matrizSerializada = new StringBuilder();
                    matrizSerializada.append("MATRIZ-");
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            matrizSerializada.append(matriz[i][j]);
                        }
                    }
                    out.println(matrizSerializada.toString());

                } else if (mensajeDelServidor.startsWith("juego")) {
                    String posicion = introducirPosicion();
                    out.println(posicion);
                }

                // Lógica del juego y comunicación entre clientes
            }
        }
    }

    public static String introducirPosicion(){
        Scanner stdIn = new Scanner(System.in);
        System.out.println("Introduce una posición de la A a la J:");
        String letra = stdIn.nextLine().toUpperCase();
        int letraConvertida = letra.charAt(0) - 65;
        System.out.println("La letra " + letra + " equivale a " + letraConvertida);

        System.out.println("Introduce una posición de 0 a 9:");
        int numero = Integer.parseInt(stdIn.nextLine());

        String posicion = letraConvertida + numero+"";
        return posicion;
    }
}