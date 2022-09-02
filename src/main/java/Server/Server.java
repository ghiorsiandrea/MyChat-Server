package Server;

import Client.Client;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        MarcoServidor miMarco = new MarcoServidor();
        miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static class MarcoServidor extends JFrame implements Runnable {
        JTextArea areatexto;

        public MarcoServidor() {
            setBounds(1200, 300, 280, 350);
            JPanel milamina = new JPanel();
            milamina.setLayout(new BorderLayout());
            areatexto = new JTextArea();
            milamina.add(areatexto, BorderLayout.CENTER);
            add(milamina);
            setVisible(true);

            // In order to the Server through the socket listens to the Client at all times, we will use a Tread
            Thread mihilo = new Thread(this);
            mihilo.start();
        }

        @Override
        public void run() {
            System.out.println("FUNCIONA");
            try {
                ServerSocket servidor = new ServerSocket(Client.PORT);

                while (true) {

                    Socket misocket = servidor.accept();
                    DataInputStream flujo_entrada = new DataInputStream(misocket.getInputStream());
                    String mensaje_texto = flujo_entrada.readUTF();
                    areatexto.append("\n" + mensaje_texto);
                    misocket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
