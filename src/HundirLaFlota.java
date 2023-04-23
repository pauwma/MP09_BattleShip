import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            System.out.println("┃  1 -     Unirse      - 1  ┃");
            System.out.println("┃  2 -     Hostear     - 2  ┃");
            System.out.println("┃  0 -      Salir      - 0  ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            opcion = scannerInt("Elige una opción (0-2): ", 0, 2);

            switch (opcion) {
                case 1:
                    try {
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Introduce la dirección IP: ");
                        String ip = scanner.nextLine();

                        if (!esDireccionIpValida(ip)) {
                            System.err.println("Error: La dirección IP ingresada no tiene un formato válido.");
                            break;
                        }

                        System.out.print("Introduce el puerto: ");
                        int puerto = scanner.nextInt();

                        ClienteHundirLaFlota cliente = new ClienteHundirLaFlota(ip, puerto);
                        cliente.inicio();
                    } catch (InputMismatchException e) {
                        System.err.println("Error: Por favor, introduzca un puerto válido.");
                    } catch (Exception e) {
                        System.err.println("Error inesperado: " + e.getMessage());
                    }
                    break;
                case 2:
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Introduzca el puerto que desea utilizar: ");
                    int puerto;
                    try {
                        puerto = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Por favor, introduce un número de puerto válido.");
                        break;
                    }

                    Thread serverThread = new Thread(() -> {
                        try {
                            ServerHundirLaFlota server = new ServerHundirLaFlota(puerto);
                            server.inicio();
                        } catch (Exception e) {
                            System.err.println("Error al iniciar el servidor: " + e.getMessage());
                        }
                    });
                    serverThread.start();

                    ClienteHundirLaFlota cliente = new ClienteHundirLaFlota("localhost", puerto);
                    try {
                        cliente.inicio();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
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

    public static boolean esDireccionIpValida(String ip) {
        if (ip.equalsIgnoreCase("localhost")) {
            return true;
        }

        String regex = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
                + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
                + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
                + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Pattern patron = Pattern.compile(regex);
        Matcher matcher = patron.matcher(ip);
        return matcher.matches();
    }
}