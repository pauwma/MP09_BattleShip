public class JuegoController {

    private int[][] tableroJ1;
    private int[][] tableroJ2;
    private boolean turno;  // ? True J1 - False J2

    public void setTableroJ1(int[][] matriz) {
        this.tableroJ1 = matriz;
    }

    public void setTableroJ2(int[][] matriz) {
        this.tableroJ2 = matriz;
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
}