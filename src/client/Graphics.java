package client;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import common.*;
import java.util.function.BiConsumer;

public class Graphics extends JFrame {

    private JPanel panel;
    private JLabel label;
    private JButton sendButton;
    private JTextField nameField;
    private JTextField lvlField;

    public Graphics(BiConsumer<String, Integer> onPlayAction) {
        setTitle("MatchMaking - Client");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));//(righe, colonne, hgap, vgap)
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        label = new JLabel("Inserisci i tuoi dati:");
        nameField = new JTextField(15);
        lvlField = new JTextField(15);
        sendButton = new JButton("Gioca");

        panel.add(new JLabel("Nickname:"));
        panel.add(nameField);
        
        panel.add(new JLabel("Livello:"));
        panel.add(lvlField);

        panel.add(sendButton);
        panel.add(label); //stato server

        add(panel);

        sendButton.addActionListener(e -> {
            String nickname = nameField.getText().trim();
            String livelloStr = lvlField.getText().trim();

            if(nickname.isEmpty() || livelloStr.isEmpty()) {
                label.setText("Compila tutti i campi");
                return;
            }

            int livello;
            try {
                livello = Integer.parseInt(livelloStr);
            } catch (NumberFormatException ex) {
                label.setText("Il livello deve essere un numero");
                return;
            }
            
            sendButton.setEnabled(false);//evita problemi di doppio click
            label.setText("Connessione in corso...");
            
            //esegue l'azione passata dal main
            onPlayAction.accept(nickname, livello);
        });

        setVisible(true);
    }

    //aggiorna lo stato della label
    public void setStatus(String text, boolean enableButton) {
        label.setText(text);
        sendButton.setEnabled(enableButton);
    }

}