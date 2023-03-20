import java.util.Scanner;

public class HundirLaFlota {

    public static void main(String[] args) {
        tutorial();
    }

    /**
     * Menú de inicio del juego.
     */
    public static void menuInicial(){
        int opcion;
        do {
            System.out.println("\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃       MENU PRINCIPAL      ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃  1 -      Jugar      - 1  ┃");
            System.out.println("┃  2 -     Tutorial    - 2  ┃");
            System.out.println("┃  0 -      Salir      - 0  ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            opcion = scannerInt("Elige una opción (0-2): ", 0, 2);

            switch (opcion) {
                case 1:
                    Tablero tablero = new Tablero();
                    tablero.colocarBarcos();
                    break;
                case 2:
                    Tablero tablero2 = new Tablero();
                    tablero2.mostrarMatrices(tablero2.getTablero(), tablero2.getTablero());
                    break;
            }
        } while (opcion != 0);
        System.out.println("Has cerrado el menú.");
    }

    public static void tutorial(){
        Scanner sc = new Scanner(System.in);
        System.out.print(
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" +
                        "\nPresiona ENTER para continuar:");
        sc.nextLine();
    }


    /**
     * Método para preguntar al usuario por un Integer con excepciones.
     * @param pregunta Pregunta para mostrar por pantalla.
     * @param min Número mínimo.
     * @param max Número máximo.
     */
    public static int scannerInt(String pregunta, int min, int max) {
        Scanner sc = new Scanner(System.in);
        int userInput = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print(pregunta);
                userInput = sc.nextInt();
                if (userInput >= min && userInput <= max) {
                    isValid = true;
                } else {
                    System.out.println("El número debe estar entre " + min + " y " + max + ".");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error al leer la entrada. Por favor, inténtalo de nuevo con un número entero.");
                sc.next();
            }
        }
        return userInput;
    }
}