/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package floressocket5client;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author alex2
 */
public class FloresSocket5Client {

    /**cliente que envia mensajes al servidor hasta que se escriba QUIT y atrapa errores 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        try (Socket socket = new Socket("192.168.127.111", 10002)) {
 
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            
            String message;
 
            do {
                System.out.print("Escribe mensaje: "); //Manda mensajes
                message = scan.nextLine();
                dos.writeUTF(message);
 
 
            } while (!message.equals("QUIT"));
 
            socket.close();
 
        } catch (UnknownHostException ex) {//atrapa error fallo al encontrar svr
 
            System.out.println("No se ha encontrado el servidor: " + ex.getMessage());
        }catch (InterruptedIOException ex){//atrapa error conexión interrumpida
            
            System.out.println("Conexión finalizada: "+ex.getMessage());
        }catch (IOException ex){
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }  
}
