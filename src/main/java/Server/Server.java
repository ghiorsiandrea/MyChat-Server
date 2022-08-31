package Server;

import javax.swing.*;
import java.awt.*;

public class Server {

    public static void main(String[] args) {
        MarcoServidor miMarco = new MarcoServidor();
        miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static class MarcoServidor extends JFrame {

        JTextArea areatexto;

        public MarcoServidor() {
            setBounds(1200, 300, 280, 350);
            JPanel milamina = new JPanel();
            milamina.setLayout(new BorderLayout());
            areatexto = new JTextArea();
            milamina.add(areatexto, BorderLayout.CENTER);
            add(milamina);
            setVisible(true);
        }
    }

}
