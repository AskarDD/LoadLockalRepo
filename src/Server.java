import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by admin on 15.06.2017.
 */
public class Server {
    public static void main(String[] args) {
        System.out.println("Program starting...");
        try {
            ServerSocket ss = new ServerSocket(9999);
            System.out.println("Server starting...");
            while(true){
                Socket s = ss.accept(); // ожидание новых клиентов
                ThreadClient socketThread = new ThreadClient(s);
                Thread t = new Thread(socketThread);
                t.start(); // запуск нового потока для каждого нового клиента
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
