import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Client starting...");
            Socket s = new Socket("localhost",9999);
            System.out.println("Connect to server...");
            Thread threadIn = new Thread(new SocketInputThread(s));// создание отдельного потока на считывание даных от сервера
            Thread threadOut = new Thread(new SockedOutputThread(s), "client1");// создание отдельного потока на ввод даных из клавиатуры
            threadIn.start();
            threadOut.start();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
