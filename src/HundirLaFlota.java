import java.util.Scanner;

public class HundirLaFlota {

    public void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Tablero.mostrarIntro();
        Tablero tablero = new Tablero();
        menuInicial();


    }

    /**
     * Menú de inicio del juego.
     */
    public void menuInicial(){
        int opcion;
        do {
            System.out.println("\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃       MENU PRINCIPAL      ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃  1 -      Jugar      - 1  ┃");
            System.out.println("┃  2 -     Tutorial    - 2  ┃");
            System.out.println("┃  0 -      Salir      - 0  ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            opcion = scannerInt("\nElige una opción (0-2): ", 0, 2);

            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
            }
        } while (opcion != 0);
        System.out.println("Has cerrado el menú.");
    }


    /**
     * Método para preguntar al usuario por un Integer con excepciones.
     * @param pregunta Pregunta para mostrar por pantalla.
     * @param min Número mínimo.
     * @param max Número máximo.
     */
    public int scannerInt(String pregunta, int min, int max) {
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