/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package floressocket5server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author alex2
 */
public class FloresSocket5Server {

    /**servidor que recibe mensajes de cliente hasta que reciba QUIT y loopea si pierde conexión.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean serveron = true;
        int port = 10002;
        System.out.println("Intentado conectar...");
        while (serveron == true){
        try (ServerSocket server = new ServerSocket(port)) {//crea server
            while (true) {
                Socket socket = server.accept();//acepta conexión
                System.out.println("Cliente conectado desde la ip "+socket.getInetAddress()+" y puerto "+socket.getPort());
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                    
                String message = dis.readUTF();
                do {
                    message = scan.nextLine();
                    System.out.println("Cliente: "+message);//recibe mensajes
 
                } while (!message.equals("QUIT"));
                serveron = false;
                System.out.println("Cliente desconectado");
                
                server.close();
                socket.close();
                
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
        }
    }

}
