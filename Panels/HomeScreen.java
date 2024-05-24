package Panels;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HomeScreen extends JPanel {
    JLabel title = new JLabel("MORTAL KOMBAT");
    public JButton hostButton = new JButton("Host Game");
    public JButton joinButton = new JButton("Join Game");

    public HomeScreen() {
        super();
        this.setPreferredSize(new Dimension(1280, 720));
        this.setLayout(null);

        //Title format:
        title.setFont(new Font("Cambria", Font.BOLD,120));
        title.setSize(400, 200);
        title.setLocation(1280/2, 50);
        this.add(title);

        //Button formatting:
        hostButton.setFont(new Font("Cambria", Font.PLAIN, 50));
        hostButton.setSize(500, 50);
        hostButton.setLocation(1280/2, 200);
        hostButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(hostButton);

        joinButton.setFont(new Font("Cambria", Font.PLAIN, 50));
        joinButton.setSize(500, 50);
        joinButton.setLocation(1280/2, 300);
        joinButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(joinButton);
    }
}
