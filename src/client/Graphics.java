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
    private JTextArea areaCompagni;

    public Graphics(BiConsumer<String, Integer> onPlayAction) {
        Settings settings = new Settings();
        setTitle("MatchMaking - Client");
        setSize(350, 450);
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
        
        panel.add(new JLabel("Livello(1-"+settings.getLivelloMassimo()+"):"));
        panel.add(lvlField);

        panel.add(sendButton);
        panel.add(label); //stato server

        add(panel);

        areaCompagni = new JTextArea(8, 20);
        areaCompagni.setEditable(false);
        add(new JScrollPane(areaCompagni), BorderLayout.SOUTH);

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

            if(livello>settings.getLivelloMassimo()||livello<1){
                label.setText("Il livello deve andare da 1 a " + settings.getLivelloMassimo());
                return;
            }
            
            sendButton.setEnabled(false);//evita problemi di doppio click
            label.setText("Connessione in corso...");
            areaCompagni.setText("");
            
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

    //mostra la lista dei compagni nella finestra
    public void mostraPartita(Match match) {
        StringBuilder sb = new StringBuilder();
        sb.append("SQUADRA 1\n");
        for (Player p : match.getGiocatoriSquadra1()) {
            sb.append(String.format("  %s  -  %s  -  lvl %d\n", p.getName(), p.getIpAddress(), p.getLvl()));
        }
        sb.append("\nSQUADRA 2\n");
        for (Player p : match.getGiocatoriSquadra2()) {
            sb.append(String.format("  %s  -  %s  -  lvl %d\n", p.getName(), p.getIpAddress(), p.getLvl()));
        }
        areaCompagni.setText(sb.toString());
        setStatus("Partita trovata", true);
    }
}