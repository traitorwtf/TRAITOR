package ChatClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by traitorwtf on 14.03.2017.
 */
public class ServerClient {

    ArrayList<PrintWriter> clients;


    public static void main(String[] args){
        ServerClient server = new ServerClient();
        server.go();
    }

    private void go(){
        clients = new ArrayList<>();
        try{
            ServerSocket serverSocket = new ServerSocket(5000);
            while(true){
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clients.add(writer);

                Thread clientThread = new Thread(new clientHandler(clientSocket));
                clientThread.start();
                System.out.println("Connection Established");
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private class clientHandler implements Runnable {
        Socket socket;
        //BufferedReader reader;
        Scanner scan;

        public clientHandler(Socket clientSocket) {
            try {
                socket = clientSocket;
                //reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                scan = new Scanner(socket.getInputStream());
            } catch (Exception e2){
                e2.printStackTrace();
            }
        }

        public void run(){
            try {
                String chatMessage;
                //while((chatMessage = reader.readLine()) != null){
                while((chatMessage = scan.next()) != null){
                    System.out.println(chatMessage);
                    tellEveryone(chatMessage);
                }
            } catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }

    private void tellEveryone(String chatMessage) {
        try{
            Iterator iter = clients.iterator();
            while (iter.hasNext()){
                PrintWriter writer = (PrintWriter) iter.next();
                writer.println(chatMessage);
                writer.flush();
            }

        } catch (Exception e1){
            e1.printStackTrace();
        }
    }
}
