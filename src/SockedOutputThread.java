import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SockedOutputThread implements Runnable {

    private Socket socket = null;
    private PrintWriter writer = null;
    private String message = null;

    public SockedOutputThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            writer = new PrintWriter(this.socket.getOutputStream());
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            boolean exit = false;
            while (!exit) {
                message = buffer.readLine();
                writer.println(Thread.currentThread().getName() + ": " + message);
                writer.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(SockedOutputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
