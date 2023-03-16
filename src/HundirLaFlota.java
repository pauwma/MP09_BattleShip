import java.util.Scanner;

public class HundirLaFlota {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[][] tablero = new char[10][10];

        // Inicializar tablero
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = '-';
            }
        }

        // Pedir al usuario los barcos
        int[] barcos = {5, 4, 4, 3, 3, 3, 2, 2, 2, 2};
        for (int i = 0; i < barcos.length; i++) {
            int longitud = barcos[i];
            System.out.println("Introduce la posición del barco de longitud " + longitud + ":");
            System.out.print("Fila: ");
            int fila = input.nextInt();
            System.out.print("Columna: ");
            int columna = input.nextInt();
            System.out.print("Orientación (H para horizontal, V para vertical): ");
            char orientacion = input.next().charAt(0);

            // Colocar el barco en el tablero
            boolean barcoColocado = false;
            while (!barcoColocado) {
                if (orientacion == 'H') {
                    if (columna + longitud <= tablero.length) {
                        barcoColocado = true;
                        for (int j = columna; j < columna + longitud; j++) {
                            if (tablero[fila][j] != '-') {
                                barcoColocado = false;
                                System.out.println("No se puede colocar aquí, hay otro barco.");
                                break;
                            }
                        }
                        if (barcoColocado) {
                            for (int j = columna; j < columna + longitud; j++) {
                                tablero[fila][j] = 'B';
                            }
                        }
                    } else {
                        System.out.println("No se puede colocar aquí, el barco se sale del tablero.");
                        break;
                    }
                } else if (orientacion == 'V') {
                    if (fila + longitud <= tablero.length) {
                        barcoColocado = true;
                        for (int j = fila; j < fila + longitud; j++) {
                            if (tablero[j][columna] != '-') {
                                barcoColocado = false;
                                System.out.println("No se puede colocar aquí, hay otro barco.");
                                break;
                            }
                        }
                        if (barcoColocado) {
                            for (int j = fila; j < fila + longitud; j++) {
                                tablero[j][columna] = 'B';
                            }
                        }
                    } else {
                        System.out.println("No se puede colocar aquí, el barco se sale del tablero.");
                        break;
                    }
                } else {
                    System.out.println("Orientación incorrecta, introduce H o V.");
                    break;
                }
            }

            // Mostrar tablero
            for (int j = 0; j < tablero.length; j++) {
                for (int k = 0; k < tablero[j].length; k++) {
                    System.out.print(tablero[j][k] + " ");
                }
                System.out.println();
            }
        }
    }
}
