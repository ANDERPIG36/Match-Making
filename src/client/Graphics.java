import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;

public class Graphics extends JFrame{

    JPanel panel;
    JLabel label;
    JButton sendButton;
    JButton closeButton;
    JTextField textField;

    public Graphics(){
        setTitle("MatchMaking - Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.panel = new JPanel();

        this.label = new JLabel("");
        this.textField = new JTextField(15);
        this.sendButton = new JButton("Gioca");

        panel.add(label);
        panel.add(textField);
        panel.add(sendButton);

        add(panel);

        setVisible(true);
    }
}