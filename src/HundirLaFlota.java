import java.util.Scanner;

public class HundirLaFlota {

    public static void main(String[] args) {
        inicio();
        menuInicial();
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
            System.out.println("┃  2 -                 - 2  ┃");
            System.out.println("┃  0 -      Salir      - 0  ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            opcion = scannerInt("Elige una opción (0-2): ", 0, 2);

            switch (opcion) {
                case 1:
                    Tablero tablero = new Tablero();
                    tablero.colocarBarcos();
                    break;
                case 2:
                    break;
            }
        } while (opcion != 0);
        System.out.println("Has cerrado el menú.");
    }

    public static void inicio(){
        Scanner sc = new Scanner(System.in);
        mostrarAscii();
        sc.nextLine();
    }

    public static void mostrarAscii(){
        System.out.print(
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                        "   --    .-\"\"-.\n" +
                        "      ) (     )\n" +
                        "     (   )   (\n" +
                        "        /     )\n" +
                        "       (_    _)                     0_,-.__\n" +
                        "         (_  )_                     |_.-._/                                                                _________\n" +
                        "          (    )                    |_--..\\                                                           <9;/  ,__-==`\n" +
                        "           (__)                     |__--_/                                                        ./~~( `)/`\n" +
                        "        |''   ``\\                   |                                                          ,-'_/ / \\ }\n" +
                        "        |        \\                  |      /b.                                                  ~       XX\\\\\n" +
                        "        |         \\  ,,,---===?A`\\  |  ,==y'                                                                \\\n" +
                        "      ___,,,,,---==\"\"\\        |M] \\ | ;|\\ |>\n" +
                        "              _   _   \\   ___,|H,,---==\"\"\"\"bno,\n" +
                        "       o  O  (_) (_)   \\ /          _     AWAW/\n" +
                        "                        /         _(+)_  dMM/\n" +
                        "         \\@_,,,,,,---==\"   \\     \\ \\|/ / MW/\n" +
                        "   --''''\"                         ===  d/\n" +
                        "                                      / /\n" +
                        "                                       ,'___________________________________     ______________________________\n" +
                        "      \\    \\    \\     \\               ,/~~~~~~~~~~~~~~~~~~   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~     ~~~~~~~\n" +
                        "                            _____    ,'  ~~~   .-\"\"-.~~~~~~           .-\"\"-.  ~~~   .-\\\"\\\"-.~~~~~~  .-\\\"\\\"-.\\n\"\n" +
                        "         .-\"\"-.           ///==---   /`-._ ..-'      -.__..-'/`-._ ..-'      -.__..-'            -.__..-' \n" +
                        "               `-.__..-' =====\\\\\\\\\\\\ V/  .---\\.\n" +
                        "                         ~~~~~~~~~~~~, _',--/_.\\  .-\"\"-.                          .-\"\"-.\n" +
                        "                               .-\"\"-.___` --  \\|         -.__..-                           -.__..-\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" +
                        "\nEs recomendado poder ver toda la imagen para jugar, presiona ENTER para continuar:");
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