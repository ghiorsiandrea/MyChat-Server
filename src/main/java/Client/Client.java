package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client {

    public static void main(String[] args) {
        MarcoCliente miMarco = new MarcoCliente();
        miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static class MarcoCliente extends JFrame {

        public MarcoCliente() {
            setBounds(600, 300, 280, 350);
            LaminaMarcoCliente milamina = new LaminaMarcoCliente();
            add(milamina);
            setVisible(true);
        }
    }

    static class LaminaMarcoCliente extends JPanel {

        private JTextField campo1;
        private JButton miboton;

        public LaminaMarcoCliente() {

            JLabel texto = new JLabel("CLIENT");
            add(texto);
            campo1 = new JTextField(20);
            add(campo1);
            miboton = new JButton("Send");
            EnviaTexto mievento = new EnviaTexto();
            miboton.addActionListener(mievento);
            add(miboton);
        }

        private class EnviaTexto implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(campo1.getText());
            }
        }


    }

}
