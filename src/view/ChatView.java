package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import controller.ChatController;
import model.GameState;

public class ChatView extends JPanel implements ActionListener {
    // Properties 
    GameState state;
    ChatController controller;
    JTextArea chatHistory;
    JTextField chatBox;
    private final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;

    // Methods
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chatBox) {
            String message = chatBox.getText();
            controller.sendMessage(message);
            chatBox.setText("");
            updateChat();
        }
    }
    public void updateChat() {
        chatHistory.setText(state.chat.toString());
    }

    // Constructor
    public ChatView(GameState state) {
        super();
        this.state = state;
        this.state.chatView = this;
        this.controller = new ChatController(state);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1280, 720));

        // add textbox at bottom
        // add chat history above textbox
        chatHistory = new JTextArea();
        chatHistory.setBounds(0, 0, 1280, 600);
        chatHistory.setEditable(false);
        this.add(chatHistory);

        chatBox = new JTextField();
        chatBox.setBounds(0, 600, 1280, 120);
        chatBox.addActionListener(this);
        this.add(chatBox);

        // ctrl-c to toggle game
        // listen on chatHistory as well
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK), "toggle game");
        this.chatHistory.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK), "toggle game");
        this.chatBox.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK), "toggle game");
        this.getActionMap().put("toggle game", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainView.cardLayout.show(MainView.panel, "gameView");
            }
        });
    }
}
