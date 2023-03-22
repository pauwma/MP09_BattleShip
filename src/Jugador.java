import java.io.*;
import java.net.*;

public class Jugador {
    private final int id;
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;

    public Jugador(Socket socket, int id) throws IOException {
        this.id = id;
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public int getId() {
        return id;
    }

    public void enviarMensaje(String mensaje) {
        out.println(mensaje);
    }

    public String recibirMensaje() throws IOException {
        return in.readLine();
    }
}