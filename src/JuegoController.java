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
        return sb.toString();
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
    public boolean procesarPosicion(String tmpPos) {

        String posData = tmpPos.substring(4); // ? Eliminar "POS-" del comienzo
        int x = Character.getNumericValue(posData.charAt(0));
        int y = Character.getNumericValue(posData.charAt(1));

        // Asumiendo que la posición es 0-indexada y la entrada es válida (dentro del rango 0-9)
        int[][] tableroActual = turno ? tableroJ1 : tableroJ2;
        int[][] tableroOponente = turno ? tableroJ2 : tableroJ1;

        // Comprobar si la posición es válida y no ha sido atacada antes
        if (tableroOponente[x][y] != -1) {
            // Atacar la posición
            int resultado = tableroOponente[x][y];
            tableroOponente[x][y] = -1; // Marcar como atacado

            // Actualizar el tablero del jugador actual con el resultado
            tableroActual[x][y] = resultado == 0 ? -1 : 2; // -1: Agua, 2: Barco hundido

            // Puedes agregar más lógica aquí para verificar si el oponente ha perdido todos sus barcos y establecer sus barcos y establecer el ganador si es necesario.
            if (resultado == 1) {
                if (todosLosBarcosHundidos(tableroOponente)) {
                    setGanador(true);
                }
                return true; // Retorna true si se hundió un barco
            }
        }
        return false; // Retorna false si no se hundió un barco o la posición ya ha sido atacada
    }

    // Método para verificar si todos los barcos en un tablero han sido hundidos
    private boolean todosLosBarcosHundidos(int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == 1) {
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