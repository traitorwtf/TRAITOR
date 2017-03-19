package ChatClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;


/**
 * Created by traitorwtf on 14.03.2017.
 */
public class Chat_Client {
    JFrame frame;
    JTextArea chatArea;
    JTextField inputArea;
    BufferedReader reader;
    PrintWriter writer;
    //Scanner scan;

    public static void main(String[] args) {
        Chat_Client client = new Chat_Client();
        client.go();
    }

    private void go() {
        frame = new JFrame("Chat Client ver. 0.61");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        frame.setResizable(false);

        chatArea = new JTextArea(25,35);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setEditable(false);
        inputArea = new JTextField(30);
        JButton button = new JButton("Send");
        JScrollPane scroll = new JScrollPane(chatArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        button.addActionListener(new buttonListener());

        panel.add(scroll);
        panel.add(inputArea);
        panel.add(button);

        frame.getContentPane().add(BorderLayout.CENTER, panel);

        establishConnection();

        Thread chatThread = new Thread(new chatRunnable());
        chatThread.start();

        frame.setVisible(true);
    }

    private void establishConnection(){
        try {
            Socket socket = new Socket("127.0.0.1", 5000);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //scan = new Scanner(socket.getInputStream());
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("Connection established");
        }
        catch(IOException e){
            e.printStackTrace();
            }
    }


    private class buttonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try {
                String message = inputArea.getText();
                writer.println(message);
                writer.flush();
                System.out.println("Text send: "+ message);
            } catch (Exception ex){
                ex.printStackTrace();
            }
            inputArea.setText("");
            inputArea.requestFocus();

        }
    }


    class chatRunnable implements Runnable {
        public void run(){
            try{
                String message;
                while ((message = reader.readLine()) != null) {
                    //while (scan.hasNext()) {
                    //System.out.println("Полученное сообщение: "+ reader.readLine());
                    //chatArea.append(scan.Next() + "\n");
                    chatArea.append(message + "\n");
                }
            } catch (Exception ex2){
                ex2.printStackTrace();
            }
        }
    }
}
