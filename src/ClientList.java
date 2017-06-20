import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by admin on 15.06.2017.
 */
public class ClientList {
    public static ArrayList<Socket> clientList = new ArrayList<Socket>();

    public synchronized static ArrayList<Socket> getClientList(){
        return clientList;
    }

    public synchronized static void addClient(Socket socket){
        System.out.println("new socket: " + socket);
        clientList.add(socket);
    }

    public synchronized static void removeClient(Socket socket) {
        clientList.remove(socket);
    }
}
