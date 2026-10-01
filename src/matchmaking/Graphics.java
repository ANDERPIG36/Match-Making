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
        setTitle("Ricetrasmettitore - Server");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.panel = new JPanel();

        this.label = new JLabel("");

        panel.add(label);
        
        add(panel);

        setVisible(true);
    }
}