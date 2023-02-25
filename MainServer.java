import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) throws IOException {
       ServerSocket socket = new ServerSocket(8001);
       while(true){
         Socket s = socket.accept();
         Server server = new Server(s);
         server.start();
         
       }  
    
    }

    
}
