import java.util.InputMismatchException;
import java.util.Scanner;

public class HundirLaFlota {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Tablero.mostrarIntro();
        int[][] tablero = new int[10][10];

        // Inicializar tablero
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = 0;
            }
        }

        colocarBarcos(tablero);
    }

    public static void colocarBarcos(int[][] matriz) {
        Scanner scanner = new Scanner(System.in);
        int[] barcos = {5, 4, 3, 3, 2, 2};
        String[] orientaciones = {"horizontal", "vertical"};

        for (int i = 0; i < barcos.length; i++) {
            boolean colocado = false;
            while (!colocado) {
                Tablero.mostrarMatriz(matriz);
                System.out.println("\nColoca tu barco de longitud " + barcos[i]);

                int fila = -1;
                while (fila == -1) {
                    System.out.print("Fila (A-J): ");
                    String filaLetra = scanner.next().toUpperCase();
                    if (filaLetra.length() == 1 && filaLetra.charAt(0) >= 'A' && filaLetra.charAt(0) <= 'J') {
                        fila = filaLetra.charAt(0) - 'A';
                    } else {
                        System.out.println("ERROR - Entrada no válida. Usa una letra entre A y J.");
                    }
                }

                int columna = -1;
                while (columna == -1) {
                    System.out.print("Columna (0-9): ");
                    try {
                        columna = scanner.nextInt();
                        if (columna < 0 || columna > 9) {
                            System.out.println("ERROR - Entrada no válida. Usa un número entre 0 y 9.");
                            columna = -1;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR - Entrada no válida. Usa un número entre 0 y 9.");
                        scanner.next();
                    }
                }

                System.out.println("Orientación: (1-Horizontal  /  2-Vertical)");
                int opcion = -1;
                while (opcion < 1 || opcion > 2) {
                    System.out.print("Elige una opción (1-2): ");
                    try {
                        opcion = scanner.nextInt();
                        if (opcion < 1 || opcion > 2) {
                            System.out.println("ERROR - Entrada no válida. Elige 1 para horizontal o 2 para vertical.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR - Entrada no válida. Elige 1 para horizontal o 2 para vertical.");
                        scanner.next();
                    }
                }
                String orientacion = orientaciones[opcion - 1];

                if (esValido(matriz, barcos[i], fila, columna, orientacion)) {
                    colocarBarco(matriz, barcos[i], fila, columna, orientacion);
                    colocado = true;
                } else {
                    System.out.println("ERROR - Posición no válida. Intenta de nuevo.");
                }
            }
        }

        Tablero.mostrarMatriz(matriz);
        System.out.println("Tablero final.");
    }

    private static boolean esValido(int[][] matriz, int longitud, int fila, int columna, String orientacion) {
        if (orientacion.equals("horizontal")) {
            if (columna + longitud > 10) {
                return false;
            }
            for (int i = 0; i < longitud; i++) {
                if (matriz[fila][columna + i] != 0) {
                    return false;
                }
            }
        } else {
            if (fila + longitud > 10) {
                return false;
            }
            for (int i = 0; i < longitud; i++) {
                if (matriz[fila + i][columna] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void colocarBarco(int[][] matriz, int longitud, int fila, int columna, String orientacion) {
        if (orientacion.equals("horizontal")) {
            for (int i = 0; i < longitud; i++) {
                matriz[fila][columna + i] = 2;
            }
        } else {
            for (int i = 0; i < longitud; i++) {
                matriz[fila + i][columna] = 2;
            }
        }
    }
}