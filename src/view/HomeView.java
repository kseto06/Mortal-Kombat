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

import network.MulticastClient;
import network.MulticastServer;
import network.SuperSocketMaster;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class HomeView extends JPanel implements ActionListener {
    //Properties
    CharacterSelectionView characterSelectionView = new CharacterSelectionView();
    JLabel title = new JLabel("MORTAL KOMBAT");
    JTextField usernameField;
    JTable serverListTable;
    //Array to store buttons and labels
    public JLabel[] labelList;
    public JButton[] buttonList;

    public JButton hostButton = new JButton("Host Game");
    //public JButton joinButton = new JButton("Scan");
    public JButton helpButton = new JButton("Help");
    BufferedImage imgBackground;

    //Server Properties:
    MulticastServer ms;
    MulticastClient mc;
    SuperSocketMaster ssm;
    String ipAddress;

    String hostPlayerName, clientPlayerName;
    Timer timer = new Timer(1000/60, this);
    Set<String> serverSet = new HashSet<>(); //Add server set without adding duplicate servers

    //Methods
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Draw the background image using Graphics g
        g.drawImage(imgBackground, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        //Loop sending messages
        if (evt.getSource() == timer) {
            try {
                ipAddress = ssm.getMyAddress();
                String sendText = hostPlayerName+","+ipAddress+","+"4019";
                ms.sendText(sendText);
                serverSet.add(sendText);
                mc.connect(); //keep trying to connect
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }

        //Receiving messages
        if (evt.getSource() == mc) {      
            System.out.println(mc.readText() + "\n"); 
            //Keep trying to update the server lists:
            this.addServer(mc.readText());
        }

        if (evt.getSource() == hostButton) {
            //One person has joined -- manipulate the characterSelectionView to have a "waiting for opponent" -- TO-DO
            ms = new MulticastServer("236.169.184.1", 15775);
            ssm = new SuperSocketMaster(4019, this);
            characterSelectionView.userType = "Host";
            hostPlayerName = usernameField.getText();
            
            //Start timer to start connection
            timer.start();

        } else if (evt.getSource() == helpButton) { //TO-DO: MC stuff here needs to be tied to a button
            //Two people now in-game, show the Character Selection Screen 
            mc = new MulticastClient("236.169.184.1", 15775, this);
            mc.connect();
            characterSelectionView.userType = "Client";
            clientPlayerName = usernameField.getText();
        
        } else if (evt.getSource() == helpButton) {
            //TO-DO: Interactive HelpScreen
        
        }
    }

    //Position serverList and buttons. Do button functionality somewhere else
    private void addServer(String readText) {
        //Necessary Labels and Arrays. Convert labelList to an Array
        labelList = new JLabel[serverSet.size()];
        buttonList = new JButton[serverSet.size()];
        Object[] serverList = serverSet.toArray();
        System.out.println(readText);
        
        for (int i = 0; i < serverSet.size(); i++) {
            //Add a label:
            labelList[i] = new JLabel(readText); //Creating a new JLabel for each server list value
            labelList[i].setFont(new Font("Cambria", Font.PLAIN, 24));
            labelList[i].setForeground(Color.WHITE);
            labelList[i].setSize(300, 60);
            labelList[i].setLocation(1280/10+210, 415 + i*65);
            this.add(labelList[i]);

            //Assign a button beside the label:
            buttonList[i] = new JButton("Join");
            buttonList[i].setFont(new Font("Cambria", Font.BOLD, 20));
            buttonList[i].setSize(80, 40);
            buttonList[i].setLocation(1280/10+710, 430 + i*65);
            buttonList[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mc.connect();
                }
            });
            this.add(buttonList[i]);

            this.repaint();
        }
    }

    //Constructor
    public HomeView() {
        super();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1280, 720));
        mc = new MulticastClient("236.169.184.1", 15775, this); //Automatically assume client and connect
        mc.connect();

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

        //Add action listeners to necessary JComponents:
        hostButton.addActionListener(this);
        helpButton.addActionListener(this);


        //Table formatting (of server lists):
        /*
         * Need to get server data
         * Initialize column names. One side is server host name, the other is button to join server
         * 
         */
    }

}
