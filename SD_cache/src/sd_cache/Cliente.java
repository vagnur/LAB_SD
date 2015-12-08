
package sd_cache;
import java.io.*;
import java.net.*;


public class Cliente {
    
    final static String HOST = "localhost";
    final static int PUERTO=5000;
    

    public static void main(String[] args) throws IOException {
        
        //Variables
        String sentence;
        String fromServer;
        
        //Buffer para recibir desde el usuario
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        
        //Socket para el cliente (host, puerto)
        Socket clientSocket = new Socket(HOST, PUERTO);
        
        //Buffer para enviar el dato al server
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        
        //Buffer para recibir dato del servidor
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        //Leemos del cliente y lo mandamos al servidor
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + '\n');
        
        //Recibimos del servidor
        fromServer = inFromServer.readLine();
        System.out.println("Server response: " + fromServer);
        
        //Cerramos el socket
        clientSocket.close();
        
    }
    
}