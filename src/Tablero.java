import java.util.InputMismatchException;
import java.util.Scanner;

public class Tablero {
    private int[][] tablero;

    /**
     * Constructor que inicia el tablero.
     */
    public Tablero (){
        this.tablero = new int[10][10];

        // ? Iniciar el tablero
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = 0;
            }
        }
    }

    /**
     * Menú de inicio del juego.
     */
    public void mostrarIntro(){
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣸⣇⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣸⣇⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⢹⡏⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "   ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⢾⡟⠛⠛⠛⠛⢻⡷⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀   ⠀⠀⠀⠀⣾⣤⡴⠟⣿⣦⣤⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀   ⠀⠀⠀⢰⡟⠋⢿⡷⠀⣿⡇⠈⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀   ⠀⠀⠀⠀⠈⣿⠀⠀⠀⠀⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀   ⠀⠀⠀⠀⠀⢻⡀⠀⠀⠀⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀   ⠀⠀⣀⣀⣀⣀⣠⣼⣧⣤⣤⣤⣿⣿⣿⣿⣧⣤⣤⣤⣀⣀⣀⣤⠀⠀⠀\n" +
                "⠀⠀⠀  ⠀⠘⠛⠛⠛⠉⠉⠉⠉⠙⠛⠛⠷⠶⢶⣦⣤⣤⣤⣴⡶⠿⠟⠛⠋⠁⠀⠀⠀\n" +
                "⠀⠀⠀   ⠀⠀⠛⠛⠛⠛⠻⠷⠶⠶⣶⣤⣤⣤⣤⣤⣤⣤⣤⣤⡶⠶⠶⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "  ___       _   _   _        _    _      \n" +
                " | _ ) __ _| |_| |_| |___ __| |_ (_)_ __ \n" +
                " | _ \\/ _` |  _|  _| / -_|_-< ' \\| | '_ \\\n" +
                " |___/\\__,_|\\__|\\__|_\\___/__/_||_|_| .__/\n" +
                "                                   |_|   ");
    }

    /**
     * Muestra el tablero de una sola matriz
     */
    public void mostrarMatriz() {
        int[][] matriz = tablero;
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String[] simbolos = {"   ", " ░ ", " █ "};

        System.out.println("   ┏━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┓");

        for (int i = 0; i < 10; i++) {
            System.out.print(" " + letras[i] + " ┃");
            for (int j = 0; j < 10; j++) {
                System.out.print(simbolos[matriz[i][j]]);
                if (j < 9) {
                    System.out.print("│");
                } else {
                    System.out.print("┃");
                }
            }
            System.out.println();
            if (i < 9) {
                System.out.println("   ┠───┼───┼───┼───┼───┼───┼───┼───┼───┼───┨");
            } else {
                System.out.println("   ┗━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┛");
            }
        }
        System.out.println("     0   1   2   3   4   5   6   7   8   9    ");
    }

    /**
     * Muestra el tablero de dos matrices.
     * @param matriz1
     * @param matriz2
     */
    public void mostrarMatrices(int[][] matriz1, int[][] matriz2) {
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String[] simbolos = {"   ", " ░ ", " █ "};

        System.out.println("                   JUGADOR 1                   │                   JUGADOR 2");
        System.out.println("   ┏━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┓   │   ┏━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┓");

        for (int i = 0; i < 10; i++) {
            System.out.print(" " + letras[i] + " ┃");
            for (int j = 0; j < 10; j++) {
                System.out.print(simbolos[matriz1[i][j]]);
                if (j < 9) {
                    System.out.print("│");
                } else {
                    System.out.print("┃");
                }
            }
            System.out.print("   │  " + " ┃");
            for (int j = 0; j < 10; j++) {
                System.out.print(simbolos[matriz2[i][j]]);
                if (j < 9) {
                    System.out.print("│");
                } else {
                    System.out.print("┃");
                }
            }
            System.out.print(" " + letras[i]);
            System.out.println();
            if (i < 9) {
                System.out.println("   ┠───┼───┼───┼───┼───┼───┼───┼───┼───┼───┨   │   ┠───┼───┼───┼───┼───┼───┼───┼───┼───┼───┨");
            } else {
                System.out.println("   ┗━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┛   │   ┗━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┛");
            }
        }
        System.out.println("     0   1   2   3   4   5   6   7   8   9     │     0   1   2   3   4   5   6   7   8   9    ");
    }

    public void colocarBarcos() {
        Scanner scanner = new Scanner(System.in);
        int[] barcos = {5, 4, 3, 3, 2, 2};
        String[] orientaciones = {"horizontal", "vertical"};

        for (int i = 0; i < barcos.length; i++) {
            boolean colocado = false;
            while (!colocado) {
                mostrarMatriz();
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

                if (esValido(tablero, barcos[i], fila, columna, orientacion)) {
                    colocarBarco(tablero, barcos[i], fila, columna, orientacion);
                    colocado = true;
                } else {
                    System.out.println("ERROR - Posición no válida. Intenta de nuevo.");
                }
            }
        }

        mostrarMatriz();
        System.out.println("Tablero final.");
    }

    private void colocarBarco(int[][] matriz, int longitud, int fila, int columna, String orientacion) {
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

    private boolean esValido(int[][] matriz, int longitud, int fila, int columna, String orientacion) {
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

    public int[][] getTablero() {
        return tablero;
    }
}

