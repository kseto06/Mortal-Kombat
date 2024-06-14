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

import model.GameState;
import model.Player;
import network.MulticastClient;
import network.MulticastServer;
import network.SuperSocketMaster;

import java.util.ArrayList;

public class HomeView extends JPanel implements ActionListener {
    //Properties
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

    GameState state;

    //Server Properties:
    MulticastServer ms;
    MulticastClient mc;
    public String ipAddress;

    Timer timer = new Timer(1500, this);
    ArrayList<String> serverList = new ArrayList<String>();

    //CardLayout cardLayout = new CardLayout();
    //JPanel cardContainer = new JPanel(cardLayout); 
    //Creating a new JPanel cardContainer allows showing new JPanel in dynamically added button (might change to boolean)
    public static boolean joinButtonPressed = false;
    public static boolean hostButtonPressed = false;

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
                String sendText = state.player1.name+","+ipAddress+","+"4019";
                ms.sendText(sendText);
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }

        //Receiving messages
        if (evt.getSource() == mc) {      
            //System.out.println(mc.readText()); 
            //Keep trying to update the server lists:
            this.addServer(mc.readText());

        }

        if (evt.getSource() == hostButton) {
            //One person has joined -- manipulate the characterSelectionView to have a "waiting for opponent" -- TO-DO
            ms = new MulticastServer("236.169.184.1", 15775);
            state.player1 = new Player(usernameField.getText());
            state.currentPlayer = state.player1;
            
            //Start timer to start connection
            timer.start();

            //Host SSM:
            state.ssm = new SuperSocketMaster(4019, state.listener);
            state.ssm.connect();
            state.ipAddress = ipAddress;

            MainView.cardLayout.show(MainView.panel, "characterSelectionView");
            hostButtonPressed = true;

        } else if (evt.getSource() == helpButton) {
            HelpView helpView = new HelpView();
            MainView.panel.add(helpView, "helpView");
            MainView.cardLayout.show(MainView.panel, "helpView");
        }
    }

    //Position serverList and buttons. Do button functionality somewhere else
    private void addServer(String readText) {
        if (serverList.contains(readText)) {
            return;
        }
        serverList.add(readText);

        for (int i = 0; i < serverList.size(); i++) {
            if (labelList != null) {
                this.remove(labelList[i]);
            }
            if (buttonList != null) {
                this.remove(buttonList[i]);
            }
        }

        labelList = new JLabel[serverList.size()];
        buttonList = new JButton[serverList.size()];

        for (int i = 0; i < serverList.size(); i++) {
            String currentMsg = serverList.get(i);

            //Add a label:
            labelList[i] = new JLabel(currentMsg);
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
            this.add(buttonList[i]);

            buttonList[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //Two people now in-game, show the Character Selection Screen 
                    state.player2 = new Player(usernameField.getText());
                    state.currentPlayer = state.player2;
                    //Client SSM:
                    state.ipAddress = currentMsg.split(",")[1];
                    state.ssm = new SuperSocketMaster(state.ipAddress, 4019, state.listener); 
                    state.ssm.connect();
                    
                    MainView.cardLayout.show(MainView.panel, "characterSelectionView");
                }
            });

            this.repaint();
        }
    }

    //Constructor
    public HomeView(GameState state) {
        super();
        this.state = state;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1280, 720));
        mc = new MulticastClient("236.169.184.1", 15775, this); //Automatically assume client and connect
        mc.connect();

        ipAddress = SuperSocketMaster.getMyAddress();

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
    }

}
