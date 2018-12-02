package projectgui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ProjectGUI extends JFrame {

   public static final String title = "Instant Messenger";
   private static ProjectGUI instance;

   private JTextPane chatWindow; //chat Window
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
        
        //Chat Window Panel
        JPanel panelChat = new JPanel(new BorderLayout());
        panel.add(panelChat, BorderLayout.CENTER);
        chatWindow = new JTextPane();
        chatWindow.setEditable(false);
        JScrollPane chatWindowSP = new JScrollPane(chatWindow);
        panelChat.add(chatWindowSP, BorderLayout.CENTER);
        
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



















/*
--- ability to hit enter instead of send button
--- send button functionality
--- cosmetic changes
--- ability to change font style and color




-- setJTextPaneFont --
        Font font = new Font("Serif", Font.PLAIN, 18);
        textChat.setFont(font);
        textChat.setForeground(Color.BLACK);


public static void setJTextPaneFont(JTextPane jtp, Font font, Color c) {
        // Start with the current input attributes for the JTextPane. This
        // should ensure that we do not wipe out any existing attributes
        // (such as alignment or other paragraph attributes) currently
        // set on the text area.
        MutableAttributeSet attrs = jtp.getInputAttributes();

        // Set the font family, size, and style, based on properties of
        // the Font object. Note that JTextPane supports a number of
        // character attributes beyond those supported by the Font class.
        // For example, underline, strike-through, super- and sub-script.
        StyleConstants.setFontFamily(attrs, font.getFamily());
        StyleConstants.setFontSize(attrs, font.getSize());
        StyleConstants.setItalic(attrs, (font.getStyle() & Font.ITALIC) != 0);
        StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);

        // Set the font color
        StyleConstants.setForeground(attrs, c);

        // Retrieve the pane's document object
        StyledDocument doc = jtp.getStyledDocument();

        // Replace the style for the entire document. We exceed the length
        // of the document by 1 so that text entered at the end of the
        // document uses the attributes.
        doc.setCharacterAttributes(0, doc.getLength() + 1, attrs, false);
    }

sendButton.addActionListener(new ActionListener(){
@Override
public void actionPerformed(ActionEvent event){
String tempMsg = msgWindow.getText();
msgWindow.setText("");
sendMessage(recipient, tempMsg);
}});




*/