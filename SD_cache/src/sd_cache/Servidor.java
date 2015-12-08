package sd_cache;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    
    static LRUCache cache = new LRUCache(5);
    static LRUCache cache1 = new LRUCache(5);
    static LRUCache cache2 = new LRUCache(5);
    static LRUCache cache3 = new LRUCache(5);
    static LRUCache cache4 = new LRUCache(5);
    static LRUCache cache5 = new LRUCache(5);
    
    
    public static void main(String[] args) throws IOException {
        
         //Variables
        String fromClient; 
        String resultado;
        
        //Socket para el servidor en el puerto 5000
        ServerSocket acceptSocket = new ServerSocket(5000);
        System.out.println("Server is running...\n");
        
        while(true){
            //Socket listo para recibir 
            Socket connectionSocket = acceptSocket.accept();
            //Buffer para recibir desde el cliente
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            //Buffer para enviar al cliente
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            
            //Recibimos el dato del cliente y lo mostramos en el server
            fromClient =inFromClient.readLine();
            System.out.println("Received: " + fromClient);
                        
            resultado = cache.getEntryFromCache(fromClient);
            
            String salida;
            
            switch(fromClient.charAt(0)){
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case '1':
                case '2':
                        resultado = cache.getEntryFromCache(fromClient);
                        System.out.println("EL RESULTADO DE LA BUSQUEDA ES: "+resultado);
            
                        salida = null;

                        if(resultado==null){//MISS
                            salida="nulo\n";
                            String respuesta = "R de C1";
                            cache.addEntryToCache(fromClient, respuesta);
                        }
                        
                        else{//HIT
                            salida = resultado+"\n";
                        }

                        //Se le envia al cliente
                        outToClient.writeBytes(salida);
                    break;
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case '3':
                case '4':
                        resultado = cache1.getEntryFromCache(fromClient);
                        System.out.println("EL RESULTADO DE LA BUSQUEDA ES: "+resultado);
            
                        salida = null;

                        if(resultado==null){//MISS
                            salida="nulo\n";
                            String respuesta = "R de C2";
                            cache1.addEntryToCache(fromClient, respuesta);
                        }
                        
                        else{//HIT
                            salida = resultado+"\n";
                        }

                        //Se le envia al cliente
                        outToClient.writeBytes(salida);
                    break;
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case '5':
                case '6':
                        resultado = cache2.getEntryFromCache(fromClient);
                        System.out.println("EL RESULTADO DE LA BUSQUEDA ES: "+resultado);
            
                        salida = null;

                        if(resultado==null){//MISS
                            salida="nulo\n";
                            String respuesta = "R de C3";
                            cache2.addEntryToCache(fromClient, respuesta);
                        }
                        
                        else{//HIT
                            salida = resultado+"\n";
                        }

                        //Se le envia al cliente
                        outToClient.writeBytes(salida);
                    break;
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case '7':
                case '8':
                        resultado = cache3.getEntryFromCache(fromClient);
                        System.out.println("EL RESULTADO DE LA BUSQUEDA ES: "+resultado);
            
                        salida = null;

                        if(resultado==null){//MISS
                            salida="nulo\n";
                            String respuesta = "R de C4";
                            cache3.addEntryToCache(fromClient, respuesta);
                        }
                        
                        else{//HIT
                            salida = resultado+"\n";
                        }

                        //Se le envia al cliente
                        outToClient.writeBytes(salida);
                    break;
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case '9':
                        resultado = cache4.getEntryFromCache(fromClient);
                        System.out.println("EL RESULTADO DE LA BUSQUEDA ES: "+resultado);
            
                        salida = null;

                        if(resultado==null){//MISS
                            salida="nulo\n";
                            String respuesta = "R de C5";
                            cache4.addEntryToCache(fromClient, respuesta);
                        }
                        
                        else{//HIT
                            salida = resultado+"\n";
                        }

                        //Se le envia al cliente
                        outToClient.writeBytes(salida);
                    break;
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                case '0':
                        resultado = cache5.getEntryFromCache(fromClient);
                        System.out.println("EL RESULTADO DE LA BUSQUEDA ES: "+resultado);
            
                        salida = null;

                        if(resultado==null){//MISS
                            salida="nulo\n";
                            String respuesta = "R de C6";
                            cache5.addEntryToCache(fromClient, respuesta);
                        }
                        
                        else{//HIT
                            salida = resultado+"\n";
                        }

                        //Se le envia al cliente
                        outToClient.writeBytes(salida);
                    break;
            }

        }
      
    }
    
}
