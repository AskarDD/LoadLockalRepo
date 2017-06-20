import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by admin on 15.06.2017.
 */
public class ThreadClient implements Runnable {
    private Socket s = null;
    private Scanner in = null;
    private PrintWriter writer = null;
    private boolean exit = false;
    private String message = null;
    private ArrayList<Socket> listSocket = null;

    public ThreadClient(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            System.out.println("User connect...");
            ClientList.addClient(s); //  добавление текущого сокета в глобальный список сокетов
            in = new Scanner(s.getInputStream());
            while (!exit) {
                message = in.nextLine();
                listSocket = ClientList.getClientList(); // получаем глобальный список сокетов для рассылки сообщения (список получателей)
                for (Socket socket : listSocket) { // отсылка сообщения всем сокетам\клиентам кроме
                    if (!socket.equals(s)) { // исключаем сокета отправителя сообщения из списка получателей
                        writer = new PrintWriter(socket.getOutputStream());
                        writer.println(message);
                        writer.flush();
                    }
                }
            }
            ClientList.removeClient(s); // если поток завершается то сокет клиента удаляется из списка сокетов
            System.out.println("User disconnect...");
        } catch (IOException ex) {
            try {
                s.close();
            } catch (IOException ex1) {
                Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
