package instant.message;
/**
 * UDPFrame.java
 *
 * Simple AWT GUI java local network messenger
 *
 * requires JDK 1.4 or later
 *
 * @author Vjacheslav K.
 *
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ProjectGUI extends JFrame {

   public static final String title = "Instant Messenger";
   private static ProjectGUI instance;

   private JTextPane textChat; //chat log
   private JTextField fieldInput; //user input
   private JButton sendButton;
   
    //Constructor
    public ProjectGUI() {
        createView();
        
        setTitle(title);
        setSize(600, 500);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }
    
    private void createView(){
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new BorderLayout());
        
        //Chat Log Panel
        JPanel panelChat = new JPanel(new BorderLayout());
        panel.add(panelChat, BorderLayout.CENTER);
        textChat = new JTextPane();
        textChat.setEditable(false);
        JScrollPane textChatSP = new JScrollPane(textChat);
        panelChat.add(textChatSP, BorderLayout.CENTER);
        
        //User Input and Send Button Panel
        JPanel panelInput = new JPanel(new BorderLayout());
        panel.add(panelInput, BorderLayout.SOUTH);
        fieldInput = new JTextField();
        panelInput.add(fieldInput, BorderLayout.CENTER);
        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            //TODO send chat message
        }
    });
        panelInput.add(sendButton, BorderLayout.EAST);
    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                instance = new ProjectGUI();
                instance.setVisible(true);
            }
        });
    }

}
