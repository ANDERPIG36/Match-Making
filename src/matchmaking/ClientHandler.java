import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;

    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            out = new PrintWriter(
                    socket.getOutputStream(), true
            );

            //riceve i dati dal client
            String nickname = in.readLine();
            int livello = Integer.parseInt(in.readLine());

            //mandare al matchmaker

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}