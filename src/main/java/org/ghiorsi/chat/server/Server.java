package org.ghiorsi.chat.server;

import org.ghiorsi.chat.protocol.PaqueteEnvio;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        MarcoServidor miMarco = new MarcoServidor();
        miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static final int PORT = Integer.parseInt(System.getenv("PORT"));

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
                ServerSocket servidor = new ServerSocket();
                servidor.bind(new InetSocketAddress("0.0.0.0" , PORT));

                //ServerSocket servidor = new ServerSocket(PORT);

                String nick, ip, mensaje;
                PaqueteEnvio paquete_recibido;
                while (true) {
                    Socket misocket = servidor.accept();

                    ObjectInputStream paquete_datos = new ObjectInputStream(misocket.getInputStream());
                    paquete_recibido = (PaqueteEnvio) paquete_datos.readObject();
                    nick = paquete_recibido.getNick();
                    ip = paquete_recibido.getIp();
                    mensaje = paquete_recibido.getMensaje();

                    if (!mensaje.equals(" online")) {
                        areatexto.append("\n" + "De: " + nick + ", para: " + ip  + " " + "\n" + "" + mensaje + "" );

                        // Puente de comunicacion por donde fluiran los datos para reenviarse
                        Socket enviaDestinatario = new Socket(ip, 9090);
                        ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());
                        paqueteReenvio.writeObject(paquete_recibido);
                        enviaDestinatario.close();
                        misocket.close();
                    } else {
                        //                    Ex.Tree - Detecta Online
                        InetAddress localizacion = misocket.getInetAddress();
                        String IpRemota = localizacion.getHostAddress();
                        System.out.println("Online con"  + IpRemota);
                    }

//                    Ex. One
//                    DataInputStream flujo_entrada = new DataInputStream(misocket.getInputStream());
//                    String mensaje_texto = flujo_entrada.readUTF();
//                    areatexto.append("\n" + mensaje_texto);
//                    misocket.close();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
