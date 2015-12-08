package sd_cache;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    
    //Se crea el cache estatico
    static LRUCache cacheEstatica = new LRUCache(6);
    //Se crean los caches dinámicos para distintos rangos de iniciales de forma de balancer cargas
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
        String salida;
        
        //Socket para el servidor en el puerto 5000
        ServerSocket acceptSocket = new ServerSocket(5000);
        System.out.println("Server is running...\n");
        
        //Llenado de cache estatico
        cacheEstatica.addEntryToCache("BS 1", "R de CE");
        cacheEstatica.addEntryToCache("BS 2", "R de CE");
        cacheEstatica.addEntryToCache("BS 3", "R de CE");
        cacheEstatica.addEntryToCache("BS 4", "R de CE");
        cacheEstatica.addEntryToCache("BS 5", "R de CE");
        cacheEstatica.addEntryToCache("BS 6", "R de CE");
        
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
                        
            String[] tokens = fromClient.split(" ");
            String parametros = tokens[1]; // PARAMETROS
            String http_method = tokens[0]; // METODO POST O GET
            String[] tokens_parametros = parametros.split("=");
            String valor = null;
            if(tokens_parametros.length==2){
                 valor = tokens_parametros[1];
            }else if(tokens_parametros.length==1 && http_method!="POST"){
                http_method="GET";
            }
            else{http_method="ERROR HTTP";} 
            
            
            switch(http_method){
                case("GET"):
                    //Se revisa si el resultado está en la caché estática
                    resultado = cacheEstatica.getEntryFromCache(fromClient);
            
                    if(resultado!=null){//Hit en estático
                        System.out.println("EL RESULTADO DE LA BUSQUEDA ES: "+resultado);
                        salida = resultado+"\n";
                        outToClient.writeBytes(salida);
                    }
                    else{//Miss en estático

                        //Se revisa en que cache se debe buscar
                        switch(fromClient.charAt(0)){
                            case 'a':case 'b':case 'c':case 'd':case 'A':case 'B':case 'C':case 'D':case '1':case '2':
                                    //Se analiza el resultado
                                    resultado = cache.getEntryFromCache(fromClient);
                                    //Y se imprime
                                    System.out.println("EL RESULTADO DE LA BUSQUEDA ES: "+resultado);

                                    //Si es un miss en la cache se informa que no está y se guarda el resultado
                                    //El resultado por ahora es solo un string estático ya que no se tiene un
                                    //front service real (además así aseguro que realmente se leyó desde la cache solicitada)
                                    if(resultado==null){//MISS
                                        salida="nulo\n";
                                        String respuesta = "R de C1";
                                        cache.addEntryToCache(fromClient, respuesta);
                                    }

                                    //Si es un hit en la cache se envia el resultado
                                    else{//HIT
                                        salida = resultado+"\n";
                                    }

                                    //Se le envia al cliente
                                    outToClient.writeBytes(salida);
                                break;
                                //Se hace el mismo procedimiento para cada cache
                            case 'e':case 'f':case 'g':case 'h':case 'E':case 'F':case 'G':case 'H':case '3':case '4':
                                    resultado = cache1.getEntryFromCache(fromClient);
                                    System.out.println("EL RESULTADO DE LA BUSQUEDA ES: "+resultado);

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
                            case 'i':case 'j':case 'k':case 'l':case 'I':case 'J':case 'K':case 'L':case '5':case '6':
                                    resultado = cache2.getEntryFromCache(fromClient);
                                    System.out.println("EL RESULTADO DE LA BUSQUEDA ES: "+resultado);

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
                            case 'm':case 'n':case 'o':case 'p':case 'M':case 'N':case 'O':case 'P':case '7':case '8':
                                    resultado = cache3.getEntryFromCache(fromClient);
                                    System.out.println("EL RESULTADO DE LA BUSQUEDA ES: "+resultado);

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
                            case 'q':case 'r':case 's':case 't':case 'Q':case 'R':case 'S':case 'T':case '9':
                                    resultado = cache4.getEntryFromCache(fromClient);
                                    System.out.println("EL RESULTADO DE LA BUSQUEDA ES: "+resultado);

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
                            case 'u':case 'v':case 'w':case 'x':case 'y':case 'z':case 'U':case 'V':case 'W':case 'X':case 'Y':case 'Z':case '0':
                                    resultado = cache5.getEntryFromCache(fromClient);
                                    System.out.println("EL RESULTADO DE LA BUSQUEDA ES: "+resultado);

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
                    
                    break;
                case("POST"):
                    switch(fromClient.charAt(0)){
                            case 'a':case 'b':case 'c':case 'd':case 'A':case 'B':case 'C':case 'D':case '1':case '2':
                                        cache.addEntryToCache(fromClient, valor);
                                        salida = "Agregado con exito en Cache\n";
                                    //Se le envia al cliente
                                    outToClient.writeBytes(salida);
                                break;
                                //Se hace el mismo procedimiento para cada cache
                            case 'e':case 'f':case 'g':case 'h':case 'E':case 'F':case 'G':case 'H':case '3':case '4':
                                        cache1.addEntryToCache(fromClient, valor); 
                                        salida = "Agregado con exito en Cache\n";
                                    //Se le envia al cliente
                                    outToClient.writeBytes(salida);
                                break;
                            case 'i':case 'j':case 'k':case 'l':case 'I':case 'J':case 'K':case 'L':case '5':case '6':
                                    cache2.addEntryToCache(fromClient, valor); 
                                        salida = "Agregado con exito en Cache\n";
                                    //Se le envia al cliente
                                    outToClient.writeBytes(salida);
                                break;
                            case 'm':case 'n':case 'o':case 'p':case 'M':case 'N':case 'O':case 'P':case '7':case '8':
                                    cache3.addEntryToCache(fromClient, valor); 
                                        salida = "Agregado con exito en Cache\n";
                                    //Se le envia al cliente
                                    outToClient.writeBytes(salida);
                                break;
                            case 'q':case 'r':case 's':case 't':case 'Q':case 'R':case 'S':case 'T':case '9':
                                    cache4.addEntryToCache(fromClient, valor); 
                                        salida = "Agregado con exito en Cache\n";
                                    //Se le envia al cliente
                                    outToClient.writeBytes(salida);
                                break;
                            case 'u':case 'v':case 'w':case 'x':case 'y':case 'z':case 'U':case 'V':case 'W':case 'X':case 'Y':case 'Z':case '0':
                                    cache5.addEntryToCache(fromClient, valor); 
                                        salida = "Agregado con exito en Cache\n";
                                    //Se le envia al cliente
                                    outToClient.writeBytes(salida);
                                break;
                        }
                    
                    break;
                default:
                    System.out.println("Incorrecto ingreso de metodo HTTP");
                    break;
            }
            
            
            

        }
      
    }
    
}
