import com.sun.tools.javac.Main;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteHundirLaFlota {
    private static String HOST = "localhost";
    //private static final String HOST = "192.168.22.109";

    private static int PUERTO = 12345;

    private boolean inicio;

    public ClienteHundirLaFlota(String ipServidor, int puerto){
        HOST = ipServidor;
        PUERTO = puerto;
    }
    public void inicio() throws IOException {
        try (Socket socket = new Socket(HOST, PUERTO)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Tablero tablero = new Tablero();

            String mensajeDelServidor;
            while ((mensajeDelServidor = in.readLine()) != null) {
                // System.out.println(mensajeDelServidor); LOGS

                if (mensajeDelServidor.startsWith("inicio")) {
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

                }
                else if (mensajeDelServidor.startsWith("turno")) {
                    if (inicio == false){
                        System.out.println("ERES EL JUGADOR 1!");
                        inicio = true;
                    }
                    String tmpPosicion = introducirPosicion();
                    out.println(tmpPosicion);
                } else if (mensajeDelServidor.startsWith("espera")){
                    if (inicio == false){
                        System.out.println("ERES EL JUGADOR 2!");
                        inicio = true;
                    }
                    System.out.println("Tu rival está haciendo su jugada, espera a tu turno...");
                } else if (mensajeDelServidor.startsWith("MATRICES-")){
                    tablero.stringToMatrices(mensajeDelServidor);
                } else if (mensajeDelServidor.startsWith("ganador")){
                    System.out.println("HAS GANADO!!!");
                    break;
                } else if (mensajeDelServidor.startsWith("perdedor")){
                    System.out.println("HAS PERDIDO :C");
                    break;
                }
            }
        }
    }

    public static String introducirPosicion(){
        Scanner stdIn = new Scanner(System.in);
        String letra;
        int posHorizontal;
        int posVertical;
        String posicion = null;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                System.out.println("Introduce una posición de la A a la J:");
                letra = stdIn.nextLine().toUpperCase();
                if (letra.length() != 1 || letra.charAt(0) < 'A' || letra.charAt(0) > 'J') {
                    throw new IllegalArgumentException("La posición debe ser una letra entre A y J.");
                }
                posHorizontal = letra.charAt(0) - 65;

                System.out.println("Introduce una posición de 0 a 9:");
                posVertical = Integer.parseInt(stdIn.nextLine());
                if (posVertical < 0 || posVertical > 9) {
                    throw new IllegalArgumentException("La posición debe ser un número entre 0 y 9.");
                }

                posicion = "POS-" + posHorizontal + "" + posVertical;
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entre 0 y 9.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return posicion;
    }

    public static void main(String[] args) {
        ClienteHundirLaFlota clienteHundirLaFlota = new ClienteHundirLaFlota("localhost", 12345);
        try {
            clienteHundirLaFlota.inicio();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}