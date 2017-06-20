import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by admin on 15.06.2017.
 */
public class SocketInputThread implements Runnable {

    private Socket s = null;
    private Scanner scanner = null;
    private String message = null;

    public SocketInputThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            scanner = new Scanner(s.getInputStream());
            while(true){
                if(scanner.hasNext()){
                    message = scanner.nextLine();
                    System.out.println(message);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketInputThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
