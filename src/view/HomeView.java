/*
 * HOME SCREEN 
 * This screen is the first one that players open up when they launch the game. They can host, join, or get help on the game.
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.util.ArrayList;

public class HomeView extends JPanel implements ActionListener {
    //Properties
    JLabel title = new JLabel("MORTAL KOMBAT");
    JTextField usernameField;
    JTable serverListTable;
    ArrayList<ArrayList<String>> tableData = new ArrayList<>(); //(Contain's hosting player's name + ip + port?) and (JButton to join)
    public JButton hostButton = new JButton("Host Game");
    public JButton joinButton = new JButton("Scan");
    public JButton helpButton = new JButton("Help");
    BufferedImage imgBackground;
    Timer timer = new Timer(1000/60, this);

    //Methods
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Draw the background image using Graphics g
        g.drawImage(imgBackground, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == timer) {
            this.repaint();
        }
    }

    //Constructor
    public HomeView() {
        super();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1280, 720));

        //Loading the image:
        //Try to read the image from both the jar file and local drive
        InputStream backgroundClass = this.getClass().getResourceAsStream("src/assets/TitleScreenBackground.jpeg");
    
        if (backgroundClass != null) {
            try {
                imgBackground = ImageIO.read(backgroundClass);
            } catch (IOException e) {
                System.out.println("Unable to read/load image from jar");
                e.printStackTrace();
            }
        } else { //If it can't be found on the jar, search it locally
            try {
                imgBackground = ImageIO.read(new File("src/assets/TitleScreenBackground.jpeg"));
            } catch (IOException e) {
                System.out.println("Unable to read/load image");
                e.printStackTrace();
            }
        }

        //Title format:
        title.setFont(new Font("Cambria", Font.BOLD,90));
        title.setForeground(Color.WHITE);
        title.setSize(1280, 200);
        title.setLocation(1280/5, 10);
        this.add(title);

        //Username text field
        usernameField = new JTextField("Enter username:");
        usernameField.setFont(new Font("Cambria", Font.ITALIC, 38));
        usernameField.setSize(500, 60);
        usernameField.setLocation(128+260, 180);
        usernameField.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(usernameField);

        //Button formatting:
        hostButton.setFont(new Font("Cambria", Font.PLAIN, 38));
        hostButton.setSize(500, 60);
        hostButton.setLocation(1280/10+260, 245);
        hostButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(hostButton);

        /* 
        joinButton.setFont(new Font("Cambria", Font.PLAIN, 38));
        joinButton.setSize(500, 60);
        joinButton.setLocation(1280/10+250, 350);
        joinButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(joinButton);
        */

        helpButton.setFont(new Font("Cambria", Font.PLAIN, 38));
        helpButton.setSize(500, 60);
        helpButton.setLocation(1280/10+260, 310);
        helpButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(helpButton);

        //"Available Server" Label Format:
        JLabel availableServerLabel = new JLabel("AVAILABLE SERVERS:");
        availableServerLabel.setFont(new Font("Cambria", Font.BOLD,38));
        availableServerLabel.setForeground(Color.WHITE);
        availableServerLabel.setSize(500, 70);
        availableServerLabel.setLocation(1280/10+260,375);
        availableServerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(availableServerLabel);


        //Table formatting (of server lists):
        /*
         * Need to get server data
         * Initialize column names. One side is server host name, the other is button to join server
         * 
         */

        timer.start();
    }

}
