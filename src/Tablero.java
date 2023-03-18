public interface Tablero {
    public static void mostrarMatriz(int[][] matriz) {
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String[] simbolos = {"   ", " ░ ", " █ "};

        System.out.println("                   JUGADOR 1                ");
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

    public static void mostrarMatrices(int[][] matriz1, int[][] matriz2) {
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

    /**
     * Muestra la imágen de entrada del juego.
     */
    public static void mostrarIntro(){
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
}

