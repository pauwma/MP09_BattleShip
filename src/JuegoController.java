import java.util.Random;

public class JuegoController {

    private boolean ganador;

    public JuegoController() {
        randomTurno();
    }

    private int[][] tableroJ1;
    private int[][] tableroJ2;
    private boolean turno;  // ? True J1 - False J2

    public void setTableroJ1(int[][] matriz) {
        this.tableroJ1 = matriz;
    }

    public void setTableroJ2(int[][] matriz) {
        this.tableroJ2 = matriz;
    }

    public void randomTurno() {
        Random random = new Random();
        turno = random.nextBoolean();
    }

    public void nextTurno() {
        turno = !turno;
    }

    public boolean isGameOver() {
        return ganador;
    }

    public void setGanador(boolean ganador) {
        this.ganador = ganador;
    }

    public String matrizToString(int[][] matriz) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                sb.append(matriz[i][j]);
            }
        }
        String enviar = "MATRIZ-" + sb.toString();
        return enviar;
    }

    public int[][] stringToMatriz(String str) {
        if (str.length() != 100) {
            throw new IllegalArgumentException("La longitud de la cadena debe ser 100.");
        }

        int[][] matriz = new int[10][10];
        int index = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matriz[i][j] = Character.getNumericValue(str.charAt(index));
                index++;
            }
        }
        return matriz;
    }

    public String matricesToString(int[][] matriz1, int[][] matriz2) {
        StringBuilder sb = new StringBuilder();
        sb.append("MATRICES-");

        for (int i = 0; i < matriz1.length; i++) {
            for (int j = 0; j < matriz1[i].length; j++) {
                if (matriz1[i][j] == -1) {
                    sb.append("X"); // Usar 'X' para representar -1
                } else {
                    sb.append(matriz1[i][j]);
                }
            }
        }

        for (int i = 0; i < matriz2.length; i++) {
            for (int j = 0; j < matriz2[i].length; j++) {
                if (matriz2[i][j] == -1) {
                    sb.append("X"); // Usar 'X' para representar -1
                } else {
                    sb.append(matriz2[i][j]);
                }
            }
        }

        return sb.toString();
    }

    public int[][] getTableroJ1() {
        return tableroJ1;
    }

    public int[][] getTableroJ2() {
        return tableroJ2;
    }

    public boolean isTurno() {
        return turno;
    }

    // Método para procesar la entrada de posición
    public boolean procesarPosicion(String posicion) {
        int x = Character.getNumericValue(posicion.charAt(0));
        int y = Character.getNumericValue(posicion.charAt(1));

        // Obtiene el tablero del jugador contrario
        int[][] tableroActual = turno ? tableroJ2 : tableroJ1;

        // Si hay un barco en la posición marcada, modifica esa posición a -1 (tocado)
        if (tableroActual[x][y] == 2) {
            tableroActual[x][y] = -1;
        } else if (tableroActual[x][y] != -1){
            // Si no había un barco, márcalo con un 1 (agua)
            tableroActual[x][y] = 1;
        }

        return true;
    }



    // Método para verificar si todos los barcos en un tablero han sido hundidos
    public boolean comprobarGanador() {
        // Obtiene el tablero del jugador contrario
        int[][] tablero = turno ? tableroJ2 : tableroJ1;

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == 2) {
                    return false; // Aún queda al menos un barco
                }
            }
        }
        return true; // No se encontraron barcos, todos han sido hundidos
    }


    public void setTurno(boolean randomTurn) {
        turno = randomTurn;
    }
}